package com.ns.mad_p1.application

import android.app.Application
import timber.log.Timber

class P1Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}