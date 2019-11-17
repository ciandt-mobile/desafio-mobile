package br.com.victoroliveira.cit.data.model

import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName("credit_id") val credit_id: String,
    @SerializedName("department") val department: String,
    @SerializedName("gender") val gender: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("job") val job: String,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profile_path: String
)