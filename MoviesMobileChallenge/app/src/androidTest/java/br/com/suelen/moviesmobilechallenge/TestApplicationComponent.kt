package br.com.suelen.moviesmobilechallenge

import android.content.Context
import br.com.suelen.mobilechallenge.data.MoviesRepository
import br.com.suelen.mobilechallenge.di.ApplicationModule
import br.com.suelen.mobilechallenge.di.MovieDetailModule
import br.com.suelen.mobilechallenge.di.PopularMoviesModule
import br.com.suelen.mobilechallenge.di.UpcomingMoviesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    UpcomingMoviesModule::class,
    PopularMoviesModule::class,
    MovieDetailModule::class,
    AndroidSupportInjectionModule::class])
interface TestApplicationComponent : AndroidInjector<TestMovieApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): TestApplicationComponent
    }


    val moviesRepository: MoviesRepository
}
