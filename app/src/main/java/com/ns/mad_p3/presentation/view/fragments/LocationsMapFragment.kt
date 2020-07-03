package com.ns.mad_p3.presentation.view.fragments

import android.content.res.Resources
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ns.mad_p3.R
import com.ns.mad_p3.data.models.LocationListFilter
import com.ns.mad_p3.presentation.contract.LocationContract
import com.ns.mad_p3.presentation.contract.ModeContract
import com.ns.mad_p3.presentation.view.states.LocationsState
import com.ns.mad_p3.presentation.viewmodel.LocationsViewModel
import com.ns.mad_p3.presentation.viewmodel.ModeViewModel
import timber.log.Timber

class LocationsMapFragment : Fragment() {
    private var map: GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        map = googleMap
        customiseMap()
        initObservers()
    }

    private val locationsViewModel: LocationContract.ViewModel by viewModel<LocationsViewModel>()
    private val modeViewModel: ModeContract.ViewModel by viewModel<ModeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locations_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.locations_map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun customiseMap() {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.

            if (modeViewModel.getCurrentMode()) map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.dark_style_json))
            else map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.light_style_json))

        } catch (e: Resources.NotFoundException) {
            Timber.e("Can't find style. Error: $e")
        }
    }

    private fun initObservers() {
        locationsViewModel.locationsState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        locationsViewModel.getAll(LocationListFilter("", "", "Oldest"))

        modeViewModel.getMode().observe(viewLifecycleOwner, Observer {
            customiseMap()
        })
    }

    private fun renderState(state: LocationsState) {
        when (state) {
            is LocationsState.Success -> {
                map?.clear()
                state.locations.forEach {
                    map?.addMarker(MarkerOptions().position(it.position).title(it.title).snippet(it.content))
                }
                if (!state.locations.isEmpty()) map?.moveCamera(CameraUpdateFactory.newLatLngZoom(state.locations[0].position, 10.0f))
            }
            is LocationsState.OperationSuccess -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            is LocationsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}