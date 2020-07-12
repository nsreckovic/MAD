package com.ns.mad_p4.data.repositories.weather

import com.ns.mad_p4.data.datasources.local.database.WeatherDao
import com.ns.mad_p4.data.datasources.remote.WeatherService
import com.ns.mad_p4.data.models.local.Resource
import com.ns.mad_p4.data.models.local.CityEntity
import com.ns.mad_p4.data.models.WeatherUI
import com.ns.mad_p4.data.models.local.WeatherForCityByDayEntity
import io.reactivex.Observable
import java.text.SimpleDateFormat

class WeatherRepositoryImpl(
    private val localDataSource: WeatherDao,
    private val remoteDataSource: WeatherService
) : WeatherRepository {

    override fun fetchByCityName(city_name: String, days: Int): Observable<Resource<Unit>> {
        return remoteDataSource
            .getByCityName(city_name, days)
            .doOnNext {
                val city = CityEntity(
                    0,
                    it.location.name,
                    it.location.country,
                    it.location.lat,
                    it.location.lon,
                    it.location.tz_id,
                    it.location.local_time
                )

                val weathersByDay = it.forecast.forecastday.map {
                    WeatherForCityByDayEntity(
                        0,
                        city.id,
                        SimpleDateFormat("yyyy-MM-dd").parse(it.date).time,
                        it.day.maxtemp_c,
                        it.day.mintemp_c,
                        it.day.avgtemp_c,
                        it.day.maxwind_kph,
                        it.day.avgvis_km,
                        it.day.avghumidity,
                        it.day.daily_chance_of_rain,
                        it.day.condition.text,
                        it.day.condition.icon,
                        it.day.condition.code,
                        it.day.uv
                    )
                }

                localDataSource.insertCity(city)
                localDataSource.insertWeatherForCityByDay(weathersByDay)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getByCityName(
        city_name: String,
        days: Int
    ): Observable<Resource<List<WeatherUI>>> {
        TODO("Not yet implemented")
    }
}