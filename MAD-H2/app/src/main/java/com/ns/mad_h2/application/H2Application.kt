package com.ns.mad_h2.application

import android.app.Application
import timber.log.Timber

class H2Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}