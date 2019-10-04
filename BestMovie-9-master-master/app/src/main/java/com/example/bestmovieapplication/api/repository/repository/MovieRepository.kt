package com.example.bestmovieapplication.api.repository.repository

import com.example.bestmovieapplication.api.repository.model.Movie
import com.example.bestmovieapplication.api.repository.model.MovieApi
import com.example.bestmovieapplication.base.repository.BaseRepository

class MovieRepository(private val api : MovieApi) : BaseRepository(), IMovieRepository {

    override suspend fun getPopularMovies() : MutableList<Movie>?{

        //Call is at BaseRepository.kt
        val movieResponse = safeApiCall(
            call = {api.getPopularMovies().await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results?.toMutableList()

    }

    override suspend fun getMovie(id: Int): Movie? {

        return safeApiCall(
            call = { api.getMovieById(id).await() },
            errorMessage = "Error Fetching Movie with Id: $id"
        )
    }

}