package com.ns.mad_p4.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import com.ns.mad_p4.data.datasources.local.database.WeatherDB

val coreModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(androidApplication().packageName, Context.MODE_PRIVATE)
    }

    single { Room.databaseBuilder(androidContext(), WeatherDB::class.java, "WeatherDB")
        .fallbackToDestructiveMigration()
        .build() }

}

