package com.apolo.findmovies.repository

import com.apolo.findmovies.data.remote.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRemoteDataSource(private val webService: WebService) {

    suspend fun getUpcominMovies() : Boolean {
        return withContext(Dispatchers.IO){
            webService.getUpcomingMovies()
        }
    }

}