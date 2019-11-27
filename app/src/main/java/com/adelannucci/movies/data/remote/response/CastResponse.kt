package com.adelannucci.movies.data.remote.response

import com.google.gson.annotations.SerializedName

data class CastResponse (
    var character: String,
    @SerializedName(value= "id")
    var remoteId: Long,
    var name: String
)
