package com.thiagoseiji.movieapp.data

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Id
import java.util.*


data class Movie(
    @Id(assignable = true) val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("genres") val genre: List<Genre>,
    @SerializedName("release_date") val releaseDate: Date,
    @SerializedName("runtime") val duration: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterImage: String,
    @SerializedName("backdrop_path") val backdropImage: String
)