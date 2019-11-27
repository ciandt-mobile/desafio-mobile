package com.adelannucci.movies.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    val page: Int,
    @SerializedName(value = "total_results")
    val totalResults: Int,
    @SerializedName(value = "total_pages")
    val totalPages: Int,
    val results: List<MovieResponse>
) {
}