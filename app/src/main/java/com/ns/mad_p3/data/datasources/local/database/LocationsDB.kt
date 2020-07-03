package com.ns.mad_p3.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ns.mad_p3.data.models.LocationEntity

@Database(
    entities = [LocationEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class LocationsDB : RoomDatabase() {
    abstract fun getLocationDao(): LocationDao
}