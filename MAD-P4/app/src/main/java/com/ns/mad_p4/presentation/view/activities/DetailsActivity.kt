package com.ns.mad_p4.presentation.view.activities

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.ns.mad_p4.R
import com.ns.mad_p4.data.models.ui.WeatherUI
import kotlinx.android.synthetic.main.activity_details.*
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val WEATHER_KEY = "WeatherKey"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var mMarker: LatLng
    private lateinit var weather: WeatherUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        init()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.details_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun init() {
        parseIntent()
        initUI()
    }

    private fun parseIntent() {
        intent?.let {
            weather = it.getParcelableExtra(WEATHER_KEY)
        }
    }

    private fun initUI() {
        initListeners()
        initData()
    }

    private fun initListeners() {
        details_LocationMarker_Iv.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(mMarker.latitude, mMarker.longitude), 7.0f))
        }
    }

    private fun initData() {
        details_CityName_Tv.text = weather.name + ", " + weather.country
        details_Date_Tv.text = SimpleDateFormat("yyyy.MM.dd").format(Date(weather.date))
        details_MaxTemp_Num_Tv.text = BigDecimal(weather.max_temp).setScale(1, RoundingMode.HALF_EVEN).toString() + " °C"
        details_AvgTemp_Num_Tv.text = BigDecimal(weather.avg_temp).setScale(1, RoundingMode.HALF_EVEN).toString() + " °C"
        details_MinTemp_Num_Tv.text = BigDecimal(weather.min_temp).setScale(1, RoundingMode.HALF_EVEN).toString() + " °C"
        details_WindSpeed_Num_Tv.text = BigDecimal(weather.max_wind_speed).setScale(1, RoundingMode.HALF_EVEN).toString() + " km/h"
        details_UVCoef_Num_Tv.text = BigDecimal(weather.uv_coef).setScale(1, RoundingMode.HALF_EVEN).toString()
        details_Humidity_Num_Tv.text = BigDecimal(weather.avg_humidity).setScale(1, RoundingMode.HALF_EVEN).toString() + " %"
        details_Visibility_Num_Tv.text = BigDecimal(weather.avg_visibility).setScale(1, RoundingMode.HALF_EVEN).toString() + " km"
        details_DailyChanceOfRain_Num_Tv.text = BigDecimal(weather.daily_chance_of_rain).setScale(2, RoundingMode.HALF_EVEN).toString() + " %"
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        customiseMap()

        mMarker = LatLng(weather.latitude, weather.longitude)
        mMap.addMarker(MarkerOptions().position(mMarker))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(mMarker.latitude, mMarker.longitude), 7.0f))
    }

    private fun customiseMap() {
        try {
            mMap.uiSettings.isMapToolbarEnabled = false
            mMap.uiSettings.isIndoorLevelPickerEnabled = false

            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.custom_map_style))
        } catch (e: Resources.NotFoundException) {
            Timber.e("Can't find style. Error: $e")
        }
    }
}