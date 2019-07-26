package com.thiagoseiji.movieapp.data

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Id
import java.util.*


data class Cast(
    @Id(assignable = true) val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String,
    @SerializedName("profile_path") val profileImage: String
)