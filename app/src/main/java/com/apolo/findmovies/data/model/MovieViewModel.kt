package com.apolo.findmovies.data.model

class MovieViewModel(
    val movieId: Int,
    val name: String,
    val overview: String,
    previousImage: String,
    bannerImage: String,
    val genreIds: List<Int>,
    val releaseDate: String
) {
    var bannerImage: String = bannerImage
    get() {
        return "https://bannerImage.tmdb.org/t/p/w500$field"
    }

    var previousImage: String = previousImage
    get() {
        return "https://image.tmdb.org/t/p/w500/$field"
    }

}