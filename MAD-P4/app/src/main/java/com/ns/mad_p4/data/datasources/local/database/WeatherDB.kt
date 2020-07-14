package com.ns.mad_p4.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ns.mad_p4.data.models.local.WeatherEntity

@Database(
    entities = [WeatherEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class WeatherDB : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao
}