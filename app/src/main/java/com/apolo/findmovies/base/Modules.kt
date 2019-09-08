package com.apolo.findmovies.base

import com.apolo.findmovies.data.remote.WebServiceClient
import com.apolo.findmovies.presentation.home.viewModel.HomeViewModel
import com.apolo.findmovies.presentation.movieDetail.viewModel.MovieDetailViewModel
import com.apolo.findmovies.repository.MoviesLocalDataSource
import com.apolo.findmovies.repository.MoviesRemoteDataSource
import com.apolo.findmovies.repository.MoviesRepository
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { MovieDetailViewModel(get()) }
}

val dataSourceModules = module {
    single { MoviesRemoteDataSource(get()) }
    single { MoviesLocalDataSource() }
}

val repositoryModules = module {
    single { MoviesRepository(get(), get()) }
}

val webServiceModules = module {
    single { WebServiceClient().service }
}


val appModules = listOf(viewModelModule, dataSourceModules, repositoryModules, webServiceModules)