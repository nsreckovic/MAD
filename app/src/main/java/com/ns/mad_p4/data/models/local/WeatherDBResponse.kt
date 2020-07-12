package com.ns.mad_p4.data.models.local

class WeatherDBResponse(
    val city: CityEntity,
    val weather_by_day: List<WeatherForCityByDayEntity>
) {
}