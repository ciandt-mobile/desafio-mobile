package br.com.victoroliveira.cit

import android.app.Application
import br.com.victoroliveira.cit.di.AppModule
import br.com.victoroliveira.cit.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetworkModule))
        }

    }
}