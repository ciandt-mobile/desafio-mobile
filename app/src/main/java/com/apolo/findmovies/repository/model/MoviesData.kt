package com.apolo.findmovies.repository.model

import com.apolo.findmovies.data.model.MovieViewModel
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
) {

    fun toViewModelList() =
        mutableListOf<MovieViewModel>().apply {
            movies.forEach { movie ->
                this.add(
                    MovieViewModel(
                        movie.id,
                        movie.original_title,
                        movie.overview,
                        movie.poster_path,
                        movie.backdrop_path,
                        movie.genre_ids,
                        movie.release_date
                    )
                )
            }
        }
}

data class Dates(
    val maximum: String,
    val minimum: String
)

data class Movie(
    val id: Int,
    val poster_path: String,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val title: String
)