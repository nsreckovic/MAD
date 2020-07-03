package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat3.nikola_sreckovic_rn3517.R
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.contract.LocationContract
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.contract.ModeContract
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.states.NewLocationState
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.viewmodel.LocationsViewModel
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.viewmodel.ModeViewModel
import timber.log.Timber
import java.sql.Timestamp
import java.util.*


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMyLocationClickListener {
    private var map: GoogleMap? = null
    private var cameraPosition: CameraPosition? = null
    private var locationMarker: Marker? = null

    private val locationsViewModel: LocationContract.ViewModel by viewModel<LocationsViewModel>()
    private val modeViewModel: ModeContract.ViewModel by viewModel<ModeViewModel>()

    // The entry point to the Places API.
    private lateinit var placesClient: PlacesClient
    // The entry point to the Fused Location Provider.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private var locationPermissionGranted = false

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private var lastKnownLocation: Location? = null

    companion object {
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

        // Keys for storing activity state.
        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION)
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }

        // Construct a PlacesClient
        Places.initialize(requireContext(), getString(R.string.google_maps_key))
        placesClient = Places.createClient(requireContext())

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Build the map.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        initListeners()
        initObservers()
    }

    // Saves the state of the map when the activity is paused.
    override fun onSaveInstanceState(outState: Bundle) {
        map?.let { map ->
            outState.putParcelable(KEY_CAMERA_POSITION, map.cameraPosition)
            outState.putParcelable(KEY_LOCATION, lastKnownLocation)
        }
        super.onSaveInstanceState(outState)
    }

    private fun initListeners() {
        map_Save_Btn.setOnClickListener {
            collapseKeyboard()
            if (locationMarker != null) {
                if (map_Title_Et.text.isNotBlank()) {
                    if (map_Content_Et.text.isNotBlank()) {
                        Timber.e(locationMarker!!.position.toString())
                        val location = LocationUI(
                            0,
                            map_Title_Et.text.toString(),
                            map_Content_Et.text.toString(),
                            Timestamp(Date().time),
                            locationMarker!!.position)

                        locationsViewModel.insert(location)

                        map_Title_Et.setText("")
                        map_Content_Et.setText("")

                    } else Toast.makeText(context, "The location description cannot be empty.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "The location name cannot be empty.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "The pin must be dropped first.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun collapseKeyboard() {
        val view: View? = activity?.currentFocus
        if (view != null) {
            val imm: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun initObservers() {
        locationsViewModel.addLocationDone.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        modeViewModel.getMode().observe(viewLifecycleOwner, Observer {
            Timber.e("Map fragment observer: $it")
            customiseMap()
        })
    }

    private fun renderState(state: NewLocationState) {
        when (state) {
            is NewLocationState.Success -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is NewLocationState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map

        // Customise the styling of the map
        customiseMap()

        // Prompt the user for permission.
        getLocationPermission()

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI()

        // Get the current location of the device and set the position of the map.
        getDeviceLocation()

        // Initialise the listeners
        map?.setOnMapLongClickListener(this)
        map?.setOnMyLocationClickListener(this)

    }

    private fun customiseMap() {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success: Boolean?

            if (modeViewModel.getCurrentMode()) success = map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.dark_style_json))
            else success = map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.light_style_json))

            if (!success!!) {
                Timber.e("Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Timber.e("Can't find style. Error: $e")
        }
    }

    // Prompts the user for permission to use the device location.
    private fun getLocationPermission() {
         // Request location permission, so that we can get the location of the
         // device. The result of the permission request is handled by a callback,
         // onRequestPermissionsResult.

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    // Handles the result of the request for location permissions.
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        Timber.e("Request callback")
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Timber.e("Dozvolio")
                    locationPermissionGranted = true
                }
            }
        }
        updateLocationUI()
        getDeviceLocation()
    }

    // Updates the map's UI settings based on whether the user has granted location permission.
    private fun updateLocationUI() {
        if (map == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                map?.isMyLocationEnabled = true
                map?.uiSettings?.isMyLocationButtonEnabled = true
            } else {
                map?.isMyLocationEnabled = false
                map?.uiSettings?.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                //getLocationPermission()
            }
        } catch (e: SecurityException) {
            Timber.e("Exception: ${e.message}")
        }
    }

    // Gets the current location of the device, and positions the map's camera.
    private fun getDeviceLocation() {
        Timber.e("usao")
         // Get the best and most recent location of the device, which may be null in rare
         // cases when a location is not available.
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude), DEFAULT_ZOOM.toFloat()))
                        }
                    } else {
                        Timber.e("Current location is null. Using defaults.")
                        Timber.e("Exception: ${task.exception}")
                        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat()))
                        map?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    override fun onMapLongClick(latLng: LatLng?) {
        map?.clear()
        val markerOptions = MarkerOptions()
        if (latLng != null) {
            markerOptions.position(latLng)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            locationMarker = map?.addMarker(markerOptions)
        }
    }

    override fun onMyLocationClick(location: Location) {
        map?.clear()
        val markerOptions = MarkerOptions()
        val latLng = LatLng(location.latitude, location.longitude)
        markerOptions.position(latLng)
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        locationMarker = map?.addMarker(markerOptions)
    }

}