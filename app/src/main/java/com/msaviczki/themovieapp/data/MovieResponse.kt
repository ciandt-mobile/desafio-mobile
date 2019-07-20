package com.msaviczki.themovieapp.data

import com.google.gson.annotations.SerializedName



data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val realeseDate: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("poster_path") val image: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("runtime") val time: String,
    @SerializedName("tagline") val tag: String,
    @SerializedName("homepage") val homePage: String
)