package com.thiagoseiji.movieapp.data

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Id
import java.util.*


data class Genre(
    @Id(assignable = true) val id: Int,
    @SerializedName("name") val name: String
)