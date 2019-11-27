package com.adelannucci.movies.data.remote

import com.adelannucci.movies.data.remote.response.MovieDetailsResponse
import com.adelannucci.movies.data.remote.response.MovieResponse

interface TheMovieDataSource {

    fun getMovies(
        filter: String,
        page: Int,
        language: String,
        success: (List<MovieResponse>) -> Unit,
        failure: () -> Unit
    )

    fun getMovie(
        id: Long,
        language: String,
        success: (MovieDetailsResponse) -> Unit,
        failure: () -> Unit
    )
}