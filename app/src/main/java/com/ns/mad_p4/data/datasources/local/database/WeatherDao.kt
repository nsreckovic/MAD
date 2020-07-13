package com.ns.mad_p4.data.datasources.local.database

import androidx.room.*
import com.ns.mad_p4.data.models.local.WeatherEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class WeatherDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(weather: List<WeatherEntity>): Completable

    @Query("SELECT * FROM weather WHERE name = :city_name AND (:date >= date AND date <= :date + :days * 86400000)")
    abstract fun getWeatherForCity(city_name: String, date: Long, days: Int): Observable<List<WeatherEntity>>

}