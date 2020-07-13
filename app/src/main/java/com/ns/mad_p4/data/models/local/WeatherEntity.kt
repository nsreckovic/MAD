package com.ns.mad_p4.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timezone_id: String,
    val local_time: String,
    val date: Long,
    val max_temp: Double,
    val min_temp: Double,
    val avg_temp: Double,
    val max_wind_speed: Double,
    val avg_visibility: Double,
    val avg_humidity: Double,
    val daily_chance_of_rain: String,
    val text: String,
    val icon: String,
    val icon_code: Int,
    val uv_coef: Double
) {
}