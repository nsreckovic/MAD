package com.ns.mad_p4.data.models.ui

import java.util.*

data class WeatherSearchParams(
    val city_name: String,
    val date: Date,
    val days: Int
) {
}