package com.adelannucci.movies.data

import com.adelannucci.movies.data.remote.TheMovieDataSource
import com.adelannucci.movies.data.remote.response.MovieResponse

class MoviesRepository(private val dataSource: TheMovieDataSource) : TheMovieDataSource {

    override fun getMostPopularMovies(
        page: Int,
        language: String,
        success: (List<MovieResponse>) -> Unit,
        failure: () -> Unit
    ) {
        dataSource.getMostPopularMovies(page, language, success, failure)
    }

}