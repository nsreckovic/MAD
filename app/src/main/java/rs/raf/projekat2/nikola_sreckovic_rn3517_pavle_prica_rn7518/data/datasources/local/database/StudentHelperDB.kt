package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.SubjectEntity

@Database(
    entities = [SubjectEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class StudentHelperDB : RoomDatabase() {
    abstract fun getSubjectDao(): SubjectDao
}