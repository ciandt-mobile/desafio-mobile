package com.msaviczki.themovieapp.data

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results") val results: MutableList<MovieResponse>?
)