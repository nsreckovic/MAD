package com.ns.mad_p4.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ns.mad_p4.data.models.remote.WeatherAPICondition

@Entity(tableName = "weather_by_day")
class WeatherForCityByDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val city_id: Int,
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
    val uv: Double
) {
}