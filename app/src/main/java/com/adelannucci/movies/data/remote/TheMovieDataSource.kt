package com.adelannucci.movies.data.remote

import com.adelannucci.movies.data.remote.response.MovieResponse

interface TheMovieDataSource {

    fun getMostPopularMovies(
        page: Int,
        language: String,
        success: (List<MovieResponse>) -> Unit,
        failure: () -> Unit
    )
}