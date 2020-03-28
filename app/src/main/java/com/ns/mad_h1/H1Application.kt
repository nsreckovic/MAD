package com.ns.mad_h1

import android.app.Application
import timber.log.Timber

class H1Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}