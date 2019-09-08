package com.apolo.findmovies.repository

import com.apolo.findmovies.data.remote.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRemoteDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies() : Boolean {
        return withContext(Dispatchers.IO){
            val request = webService.getUpcomingMovies().execute()
            request.isSuccessful
        }
    }

}