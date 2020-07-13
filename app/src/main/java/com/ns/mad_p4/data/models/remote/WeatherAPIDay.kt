package com.ns.mad_p4.data.models.remote

data class WeatherAPIDay(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val avgtemp_c: Double,
    val maxwind_kph: Double,
    val avgvis_km: Double,
    val avghumidity: Double,
    val daily_chance_of_rain: String,
    val condition: WeatherAPICondition,
    val uv: Double
) {
}