package com.adelannucci.movies.data.network

import androidx.lifecycle.LiveData

interface TheMovieDataSource {

    val downloadedCurrentWeather: LiveData<Any>

    suspend fun fetch(page: Int, language: String)
}