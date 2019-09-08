package com.apolo.findmovies.base

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.lang.ref.WeakReference

class AppAplication : Application() {

    companion object {

        private var sContext: WeakReference<Context>? = null

        val context: Context?
            get() = sContext?.get()

    }

    override fun onCreate() {
        super.onCreate()

        sContext = WeakReference(applicationContext)

        startKoin {
            androidLogger()
            androidContext(this@AppAplication)
            modules(appModules)
        }
    }


}