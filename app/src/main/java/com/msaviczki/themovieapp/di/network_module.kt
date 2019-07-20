package com.msaviczki.themovieapp.di

import com.msaviczki.themovieapp.network.core.ApiClient
import com.msaviczki.themovieapp.network.networkinterface.MovieApi
import org.koin.dsl.module


val movieApi: MovieApi by lazy {
    ApiClient.client.create(MovieApi::class.java)
}

val networkModule = module {
    single { movieApi }
}