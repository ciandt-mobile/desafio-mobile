package com.msaviczki.themovieapp.di

import com.msaviczki.themovieapp.presentation.home.viewmodel.HomeViewModel
import com.msaviczki.themovieapp.presentation.home.viewmodel.HomeViewModelRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }

    factory { HomeViewModelRepository(get()) }
}