package com.example.bestmovieapplication.api.repository.repository

import com.example.bestmovieapplication.api.repository.model.Movie

interface IMovieRepository{

    suspend fun getPopularMovies(): MutableList<Movie>?

    suspend fun getMovie(id: Int): Movie?

}