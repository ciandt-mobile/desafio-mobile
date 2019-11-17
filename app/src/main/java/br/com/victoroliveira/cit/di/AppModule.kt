package br.com.victoroliveira.cit.di

import br.com.victoroliveira.cit.presentation.detail.DetailAdapter
import br.com.victoroliveira.cit.presentation.detail.DetailViewModel
import br.com.victoroliveira.cit.presentation.popular.PopularAdapter
import br.com.victoroliveira.cit.presentation.popular.PopularViewModel
import br.com.victoroliveira.cit.presentation.upcoming.UpcomingAdapter
import br.com.victoroliveira.cit.presentation.upcoming.UpcomingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel { UpcomingViewModel(get()) }
    viewModel { PopularViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    factory { UpcomingAdapter() }
    factory { PopularAdapter() }
    factory { DetailAdapter() }
    single { createMovieRepository(get()) }
}