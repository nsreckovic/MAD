package com.ns.mad_p4.data.datasources.local.database

import androidx.room.*
import com.ns.mad_p4.data.models.local.CityEntity
import com.ns.mad_p4.data.models.local.WeatherForCityByDayEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class WeatherDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertCity(city: CityEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertWeatherForCityByDay(entities: List<WeatherForCityByDayEntity>): Completable

    @Query("SELECT * FROM city WHERE name LIKE :city_name")
    abstract fun getCity(city_name: String): Observable<CityEntity>

    @Query("SELECT * FROM weather_by_day WHERE city_id = :city_id AND (:date >= date AND date <= :date + :days * 86400000)")
    abstract fun getWeatherByDayForCity(city_id: Int, date: Long, days: Int): Observable<WeatherForCityByDayEntity>

}