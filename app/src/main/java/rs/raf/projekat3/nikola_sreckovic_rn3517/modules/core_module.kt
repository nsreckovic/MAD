package rs.raf.projekat3.nikola_sreckovic_rn3517.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.datasources.local.database.LocationsDB

val coreModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(androidApplication().packageName, Context.MODE_PRIVATE)
    }

    single { Room.databaseBuilder(androidContext(), LocationsDB::class.java, "LocationsDB")
        .fallbackToDestructiveMigration()
        .build() }

}

