package com.msaviczki.themovieapp.di

import com.msaviczki.themovieapp.presentation.detail.viewmodel.MovieDetailViewModel
import com.msaviczki.themovieapp.presentation.detail.viewmodel.MovieDetailViewModelRepository
import com.msaviczki.themovieapp.presentation.detail.viewmodel.MovieDetailViewModelRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailModule = module {
    viewModel { MovieDetailViewModel(get(), get()) }

    factory { MovieDetailViewModelRepositoryImpl(get()) as MovieDetailViewModelRepository }
}