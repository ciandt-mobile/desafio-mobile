package com.brunodiegom.movies.model

import com.google.gson.annotations.SerializedName

/**
 * Data object use to parse the [Detail] data get from server.
 */
data class Detail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("runtime")
    val duration: Int,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("overview")
    val overview: String
) {
    companion object {
        const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w360"
        const val EXTRA_ID = "extra_id"
    }
}
