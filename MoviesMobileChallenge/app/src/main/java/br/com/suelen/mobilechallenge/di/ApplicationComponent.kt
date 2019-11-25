package br.com.suelen.mobilechallenge.di

import android.content.Context
import br.com.suelen.mobilechallenge.MoviesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        UpcomingMoviesModule::class,
        PopularMoviesModule::class,
        MovieDetailModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MoviesApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext : Context) : ApplicationComponent
    }
}