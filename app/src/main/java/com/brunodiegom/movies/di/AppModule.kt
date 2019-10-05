package com.brunodiegom.movies.di

import com.brunodiegom.movies.api.Endpoint
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val endpointModules = module {
    single { Endpoint() }
}

val repositoryModules = module {
    single { MovieRepository(get()) }
}

val viewModelModules = module {
    viewModel { MainActivityViewModel(get()) }
}

val appComponent: List<Module> = listOf(endpointModules, repositoryModules, viewModelModules)
