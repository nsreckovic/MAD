package com.ns.mad_p4.data.models.remote

data class WeatherAPIResponse(
    val location: WeatherAPILocation,
    val forecast: WeatherAPIForecast
) {
}