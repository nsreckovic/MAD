package rs.raf.projekat3.nikola_sreckovic_rn3517.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationEntity

@Database(
    entities = [LocationEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class LocationsDB : RoomDatabase() {
    abstract fun getLocationDao(): LocationDao
}