package com.adelannucci.movies.data

import com.adelannucci.movies.data.remote.TheMovieDataSource
import com.adelannucci.movies.data.remote.response.MovieResponse

class MoviesRepository(private val dataSource: TheMovieDataSource) : TheMovieDataSource {

    override fun getMovies(
        filter: String,
        page: Int,
        language: String,
        success: (List<MovieResponse>) -> Unit,
        failure: () -> Unit
    ) {
        dataSource.getMovies(filter, page, language, success, failure)
    }

}