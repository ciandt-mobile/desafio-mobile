package com.apolo.findmovies.repository

import com.apolo.findmovies.data.model.MovieCreditsViewModel
import com.apolo.findmovies.repository.model.GenresResponse
import com.apolo.findmovies.repository.model.MoviesResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class MoviesRepository(private val moviesRemoteDataSource: MoviesRemoteDataSource, private val moviesLocalDataSource: MoviesLocalDataSource) {

    suspend fun getUpcomingMovies(nextPage : Int) : MoviesResponse? {
        return withContext(IO){
            try {
                moviesRemoteDataSource.getUpcomingMovies(nextPage)
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getPopularMovies(nextPage : Int) : MoviesResponse? {
        return withContext(IO){
            try {
                moviesRemoteDataSource.getPopularMovies(nextPage)
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getGenres() : GenresResponse? {
        if (moviesLocalDataSource.hasGenres()) {
            return moviesLocalDataSource.getGenres()
        }

        return withContext(IO){
            try {
                moviesRemoteDataSource.getGenres()?.apply {
                    moviesLocalDataSource.saveGenres(this)
                }
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getCredits(movieId : Int) : List<MovieCreditsViewModel>? {
        return withContext(IO){
            try {
                moviesRemoteDataSource.getCredits(movieId)?.toViewModel()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }


}