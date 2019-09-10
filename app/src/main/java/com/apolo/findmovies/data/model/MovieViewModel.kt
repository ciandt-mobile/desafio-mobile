package com.apolo.findmovies.data.model

import java.io.Serializable

class MovieViewModel(
    val movieId: Int,
    val name: String,
    val overview: String,
    previousImage: String?,
    bannerImage: String?,
    val genreIds: List<Int>,
    val releaseDate: String,
    val duration : String = "196m"
) : Serializable {
    var bannerImage: String? = bannerImage
    get() {
        return "https://image.tmdb.org/t/p/w500/$field"
    }

    var previousImage: String? = previousImage
    get() {
        return "https://image.tmdb.org/t/p/w500/$field"
    }

}