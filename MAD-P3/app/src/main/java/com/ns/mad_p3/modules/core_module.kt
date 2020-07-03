package com.ns.mad_p3.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import com.ns.mad_p3.data.datasources.local.database.LocationsDB

val coreModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(androidApplication().packageName, Context.MODE_PRIVATE)
    }

    single { Room.databaseBuilder(androidContext(), LocationsDB::class.java, "LocationsDB")
        .fallbackToDestructiveMigration()
        .build() }

}

