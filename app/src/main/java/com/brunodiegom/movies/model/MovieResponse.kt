package com.brunodiegom.movies.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for server request result.
 */
data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
