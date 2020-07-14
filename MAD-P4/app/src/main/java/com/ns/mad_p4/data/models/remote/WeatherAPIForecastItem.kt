package com.ns.mad_p4.data.models.remote

data class WeatherAPIForecastItem(
    val date: String,
    val day: WeatherAPIDay
) {
}