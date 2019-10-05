package com.brunodiegom.movies.model

import com.google.gson.annotations.SerializedName

/**
 * Data object to parse the [Genre] data.
 */
data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
