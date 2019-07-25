package com.thiagoseiji.movieapp

import android.app.Application
import com.thiagoseiji.movieapp.di.DiModules
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            this@MoviesApp
        modules(listOf(DiModules.appModule))}

    }
}