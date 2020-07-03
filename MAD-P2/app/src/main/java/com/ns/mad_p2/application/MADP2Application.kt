package com.ns.mad_p2.application

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.ns.mad_p2.modules.coreModule
import com.ns.mad_p2.modules.noteModule
import com.ns.mad_p2.modules.subjectModule
import com.ns.mad_p2.modules.userModule
import timber.log.Timber

class MADP2Application : Application() {

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
            userModule,
            subjectModule,
            noteModule
        )
        startKoin {
            androidLogger(Level.DEBUG)
            // Use application context
            androidContext(this@MADP2Application)
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