package com.brunodiegom.movies

import android.app.Application
import com.brunodiegom.movies.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Initialize the dependency injection on app initialization.
 */
class InitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InitApplication)
            modules(appComponent)
        }
    }
}
