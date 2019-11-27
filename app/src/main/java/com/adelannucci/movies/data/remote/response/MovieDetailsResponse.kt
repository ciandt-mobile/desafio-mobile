package com.adelannucci.movies.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    var runtime: Int,
    var video: Boolean,
    @SerializedName(value = "poster_path")
    var posterPath: String,
    @SerializedName(value = "id")
    var remoteId: Long,
    @SerializedName(value = "backdrop_path")
    var backdropPath: String,
    @SerializedName(value = "original_title")
    var originalTitle: String,
    @SerializedName(value = "genres")
    var genres: List<GenreResponse>,
    var title: String,
    var overview: String,
    @SerializedName(value = "release_date")
    var releaseDate: String,
    var casts: CastsResponse
)