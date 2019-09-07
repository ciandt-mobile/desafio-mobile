package com.apolo.findmovies.repository

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class MoviesRepository(private val moviesRemoteDataSource: MoviesRemoteDataSource) {

    suspend fun getUpcominMovies() : Boolean {
        return withContext(IO){
            moviesRemoteDataSource.getUpcominMovies()
        }
    }
}