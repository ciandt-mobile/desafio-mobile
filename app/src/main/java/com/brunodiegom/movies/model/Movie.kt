package com.brunodiegom.movies.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Data model returned from server.
 */
data class Movie(
    @SerializedName("poster_path")
    val posterUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val overview: String
) : Serializable
