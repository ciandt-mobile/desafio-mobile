package br.com.suelen.mobilechallenge.di

import br.com.suelen.mobilechallenge.data.*
import br.com.suelen.mobilechallenge.data.RemoteMoviesDataSource
import br.com.suelen.mobilechallenge.utilities.RetrofitService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteMoviesDataSource

    @JvmStatic
    @Singleton
    @RemoteMoviesDataSource
    @Provides
    fun provideRemoteMovieDataSource(moviesApi: MoviesApi): MoviesDataSource {
        return RemoteMoviesDataSource(
            moviesApi
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideMoviesApi() : MoviesApi {
        return RetrofitService.createService(MoviesApi::class.java)
    }
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: MoviesRepositoryImpl): MoviesRepository
}