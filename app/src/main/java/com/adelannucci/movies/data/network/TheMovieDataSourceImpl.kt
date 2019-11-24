package com.adelannucci.movies.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adelannucci.movies.data.ApiTheMovieService
import com.adelannucci.movies.internal.NoConnectivityException

class TheMovieDataSourceImpl(private val api: ApiTheMovieService) : TheMovieDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<Any>()
    override val downloadedCurrentWeather: LiveData<Any>
        get() = _downloadedCurrentWeather

    override suspend fun fetch(page: Int, languageCode: String) {
        try {
            val fetched = api.getDiscoverMovie(page, languageCode)
            _downloadedCurrentWeather.postValue(fetched)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}