package br.com.victoroliveira.cit.data.model

import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("dates") val dates: Dates,
    @SerializedName("total_pages") val total_pages: Int
)

