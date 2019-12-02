package br.com.josericardooliveira.desafiomobile.model

import com.google.gson.annotations.SerializedName

data class CastQuery (

    @SerializedName("id") val id : Int,
    @SerializedName("cast") val cast : List<Cast>
)

data class Cast (

//    @SerializedName("cast_id") val castId : Int,
    @SerializedName("character") val character : String,
//    @SerializedName("credit_id") val creditId : String,
//    @SerializedName("gender") val gender : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
//    @SerializedName("order") val order : Int,
    @SerializedName("profile_path") val profilePath : String
)