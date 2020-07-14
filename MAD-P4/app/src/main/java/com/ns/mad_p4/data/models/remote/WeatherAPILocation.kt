package com.ns.mad_p4.data.models.remote

data class WeatherAPILocation(
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime: String
) {
}