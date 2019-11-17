package br.com.victoroliveira.cit.data.model

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("cast") val cast : List<Cast>,
    @SerializedName("crew") val crew : List<Crew>
)