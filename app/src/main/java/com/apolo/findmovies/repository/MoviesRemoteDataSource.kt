package com.apolo.findmovies.repository

import android.util.Log
import com.apolo.findmovies.data.remote.WebService
import com.apolo.findmovies.repository.model.GenresResponse
import com.apolo.findmovies.repository.model.MovieCreditsResponse
import com.apolo.findmovies.repository.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRemoteDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(nextPage : Int) : MoviesResponse? {
        return withContext(Dispatchers.IO){
            try {
                val request = webService.getUpcomingMovies(nextPage).execute()

                if (request.isSuccessful) {
                    Log.d("Successful Request", "Ok")
                } else {
                    Log.d("Error Request", "Error")
                }


                request.body()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getPopularMovies(nextPage : Int) : MoviesResponse? {
        return withContext(Dispatchers.IO){
            try {
                val request = webService.getPopularMovies(nextPage).execute()

                if (request.isSuccessful) {
                    Log.d("Successful Request", "Ok")
                } else {
                    Log.d("Error Request", "Error")
                }
                request.body()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getGenres() : GenresResponse? {
        return withContext(Dispatchers.IO){
            try {
                val request = webService.getMoviesGenres().execute()

                if (request.isSuccessful) {
                    Log.d("Successful Request", "Ok")
                } else {
                    Log.d("Error Request", "Error")
                }
                request.body()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getCredits(movieId : Int) : MovieCreditsResponse? {
        return withContext(Dispatchers.IO){
            try {
                val request = webService.getCredits(movieId).execute()

                if (request.isSuccessful) {
                    Log.d("Successful Request", "Ok")
                } else {
                    Log.d("Error Request", "Error")
                }
                request.body()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }



}