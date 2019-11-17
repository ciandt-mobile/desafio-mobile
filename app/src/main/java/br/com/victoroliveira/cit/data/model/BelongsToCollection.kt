package br.com.victoroliveira.cit.data.model

import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("backdrop_path") val backdrop_path : String
)