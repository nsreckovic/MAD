package com.ns.mad_p4.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.ns.mad_p4.modules.coreModule
import com.ns.mad_p4.modules.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MADP4Application : Application() {

    companion object {
        const val WEATHER_API_KEY = "6d590680660b4c44ad5121505201107"
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
        initStetho()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            weatherModule
        )
        startKoin {
            androidLogger(Level.DEBUG)
            // Use application context
            androidContext(this@MADP4Application)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

}