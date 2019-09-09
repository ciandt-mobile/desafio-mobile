package com.apolo.findmovies.data.model

import java.io.Serializable

class MovieCreditsViewModel(
    image: String,
    val actorName: String,
    val roleName: String) : Serializable {

    val image: String? = image
    get() {
        return "https://image.tmdb.org/t/p/w500/$field"
    }

}