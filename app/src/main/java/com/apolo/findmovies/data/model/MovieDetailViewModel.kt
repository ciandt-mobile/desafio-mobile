package com.apolo.findmovies.data.model

import java.io.Serializable

class MovieDetailViewModel(
    val image: String,
    val name: String,
    val releaseDate: String,
    val duration: String,
    val category: String,
    val description: String
) : Serializable
