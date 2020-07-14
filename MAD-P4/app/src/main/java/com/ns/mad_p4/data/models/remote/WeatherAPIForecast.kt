package com.ns.mad_p4.data.models.remote

data class WeatherAPIForecast(
    val forecastday: List<WeatherAPIForecastItem>
) {
}