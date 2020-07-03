package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.activities

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_edit_location.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat3.nikola_sreckovic_rn3517.R
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.contract.LocationContract
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.contract.ModeContract
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.viewmodel.LocationsViewModel
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.viewmodel.ModeViewModel
import timber.log.Timber
import java.text.SimpleDateFormat

class EditLocationActivity : AppCompatActivity(R.layout.activity_edit_location), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {
        const val LOCATION_KEY = "LocationKey"
    }

    private lateinit var location: LocationUI
    private val locationsViewModel: LocationContract.ViewModel by viewModel<LocationsViewModel>()
    private val modeViewModel: ModeContract.ViewModel by viewModel<ModeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (modeViewModel.getCurrentMode()) setTheme(R.style.DarkTheme)
        else setTheme(R.style.LightTheme)

        super.onCreate(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        init()
    }

    private fun init() {
        parseIntent()
        initUi()
    }

    private fun initUi() {
        initListeners()
        initData()
    }

    private fun initData() {
        edit_location_Title_Et.setText(location.title)
        edit_location_Content_Et.setText(location.content)
        edit_location_Timestamp_TV.setText(SimpleDateFormat("dd.MM.yyyy HH:mm").format(location.timestamp))
    }

    private fun initListeners() {
        edit_location_marker.setOnClickListener {
            placeMarker()
        }
        edit_location_Cancel_Btn.setOnClickListener {
            finish()
        }
        edit_location_Save_Btn.setOnClickListener {
            collapseKeyboard()
            val title = edit_location_Title_Et.text.toString()
            val content = edit_location_Content_Et.text.toString()
            if (title.isNotBlank()) {
                if (content.isNotBlank()) {
                    if (title != location.title || content != location.content) {
                        location.title = title
                        location.content = content
                        locationsViewModel.update(location)
                    }
                    finish()
                } else Toast.makeText(this, "The location description cannot be empty.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "The location name cannot be empty.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun collapseKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun parseIntent() {
        intent?.let {
            location = it.getParcelableExtra(LOCATION_KEY)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        customiseMap()

        placeMarker()
    }

    private fun customiseMap() {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success: Boolean?

            if (modeViewModel.getCurrentMode()) success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.dark_style_json))
            else success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.light_style_json))

            if (!success) {
                Timber.e("Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Timber.e("Can't find style. Error: $e")
        }
    }

    private fun placeMarker() {
        val latLng = LatLng(location.position.latitude, location.position.longitude)
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(latLng).title(location.title))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f))
    }
}