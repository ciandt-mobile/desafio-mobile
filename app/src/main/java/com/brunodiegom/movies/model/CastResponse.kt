package com.brunodiegom.movies.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for server request result.
 */
data class CastResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<Cast>
)
