package com.thiagoseiji.movieapp.di

import com.thiagoseiji.movieapp.repository.MovieRepository
import com.thiagoseiji.movieapp.retrofit.MovieService
import com.thiagoseiji.movieapp.viewmodel.MovieDetailViewModel
import com.thiagoseiji.movieapp.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object DiModules {

    val appModule = module {
        single { MovieService.create() }

        single { MovieRepository(get()) }

        viewModel { MoviesViewModel( get()) }
        viewModel { MovieDetailViewModel( get()) }
    }
}