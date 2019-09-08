package com.apolo.findmovies.repository

import com.apolo.findmovies.repository.model.GenresResponse
import com.apolo.findmovies.repository.model.MoviesResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.lang.Exception

class MoviesRepository(private val moviesRemoteDataSource: MoviesRemoteDataSource, private val moviesLocalDataSource: MoviesLocalDataSource) {

    suspend fun getUpcomingMovies() : MoviesResponse? {
        return withContext(IO){
            try {
                moviesRemoteDataSource.getUpcomingMovies()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getPopularMovies() : MoviesResponse? {
        return withContext(IO){
            try {
                moviesRemoteDataSource.getPopularMovies()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getGenres() : Boolean? {
        if (moviesLocalDataSource.hasGenders()) {
            return true
        }

        return withContext(IO){
            try {
                moviesRemoteDataSource.getGenres()?.let{
                    moviesLocalDataSource.saveGenres(it)
                    true
                }
            } catch (exception : Exception) {
                throw exception
            }
        }
    }


}