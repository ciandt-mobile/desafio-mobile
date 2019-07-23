package com.msaviczki.themovieapp.di

import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContext
import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContextImpl
import com.msaviczki.themovieapp.presentation.movie.viewmodel.MovieViewModel
import com.msaviczki.themovieapp.presentation.movie.viewmodel.MovieViewModelRepository
import com.msaviczki.themovieapp.presentation.movie.viewmodel.MovieViewModelRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel { MovieViewModel(get(), get()) }

    single { CoroutineBaseContextImpl() as CoroutineBaseContext }
    factory { MovieViewModelRepositoryImpl(get()) as MovieViewModelRepository }
}