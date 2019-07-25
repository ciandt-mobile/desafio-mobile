package com.thiagoseiji.movieapp.data.api

import com.google.gson.annotations.SerializedName
import com.thiagoseiji.movieapp.data.Movie


data class MovieResponse(
    @SerializedName("results")
    val results: List<Movie>
)