package br.com.architerure.movies.api.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("results")
    val movies : List<Movie>
)