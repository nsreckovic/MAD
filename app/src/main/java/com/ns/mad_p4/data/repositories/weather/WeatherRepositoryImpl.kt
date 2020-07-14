package com.ns.mad_p4.data.repositories.weather

import com.ns.mad_p4.data.datasources.local.database.WeatherDao
import com.ns.mad_p4.data.datasources.remote.WeatherService
import com.ns.mad_p4.data.models.local.Resource
import com.ns.mad_p4.data.models.local.WeatherEntity
import com.ns.mad_p4.data.models.ui.WeatherUI
import io.reactivex.Observable
import timber.log.Timber
import java.text.SimpleDateFormat

class WeatherRepositoryImpl(
    private val localDataSource: WeatherDao,
    private val remoteDataSource: WeatherService
) : WeatherRepository {

    override fun fetchByCityName(city_name: String, days: Int): Observable<Resource<Unit>> {
        return remoteDataSource
            .getByCityName(city_name, days)
            .doOnNext {
                Timber.e(it.toString())
                val city_name = it.location.name
                val country = it.location.country
                val latitude = it.location.lat
                val longitude = it.location.lon
                val timezone = it.location.tz_id
                val local_time = it.location.localtime

                val weather = it.forecast.forecastday.map {
                    WeatherEntity(
                        "",
                        city_name,
                        country,
                        latitude,
                        longitude,
                        timezone,
                        local_time,
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

                localDataSource.insertAll(weather).blockingAwait()
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getByCityName(
        city_name: String,
        date: Long,
        days: Int
    ): Observable<Resource<List<WeatherUI>>> {
        Timber.e(city_name)
        return localDataSource
            .getWeatherForCity(city_name, date, date + (days - 1) * 86400000)
            .map {
                val weather = it.map {
                    WeatherUI(
                        it.id,
                        it.name,
                        it.country,
                        it.latitude,
                        it.longitude,
                        it.timezone_id,
                        it.local_time,
                        it.date,
                        it.max_temp,
                        it.min_temp,
                        it.avg_temp,
                        it.max_wind_speed,
                        it.avg_visibility,
                        it.avg_humidity,
                        it.daily_chance_of_rain,
                        it.text,
                        it.icon,
                        it.icon_code,
                        it.uv_coef
                    )
                }
                Resource.Success(weather)
            }
    }
}