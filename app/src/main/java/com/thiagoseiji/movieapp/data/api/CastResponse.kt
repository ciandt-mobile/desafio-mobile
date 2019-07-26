package com.thiagoseiji.movieapp.data.api

import com.google.gson.annotations.SerializedName
import com.thiagoseiji.movieapp.data.Cast
import com.thiagoseiji.movieapp.data.Genre
import com.thiagoseiji.movieapp.data.Movie


data class CastResponse(
    @SerializedName("cast")
    val results: List<Cast>
)