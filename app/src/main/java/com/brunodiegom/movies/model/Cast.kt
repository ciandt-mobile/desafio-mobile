package com.brunodiegom.movies.model

import com.google.gson.annotations.SerializedName

/**
 * Data model of [Cast] returned from server.
 */
data class Cast(
    @SerializedName("cast_id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("profile_path")
    val picture: String
)
