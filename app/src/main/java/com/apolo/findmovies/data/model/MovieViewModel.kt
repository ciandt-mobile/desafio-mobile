package com.apolo.findmovies.data.model

class MovieViewModel(
    val movieId: Int,
    val name: String,
    val overview: String,
    image: String,
    val genreIds: List<Int>,
    val releaseDate: String
) {

    var image: String = image
    set(value) {
        field = "https://image.tmdb.org/t/p/w500$value"
    }

}