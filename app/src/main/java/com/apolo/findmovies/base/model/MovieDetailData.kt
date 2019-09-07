package com.apolo.findmovies.base.model

import java.io.Serializable

class MovieDetailViewModel(
    val image: String,
    val name: String,
    val releaseDate: String,
    val duration: String,
    val category: String,
    val description: String,
    val moviesInfo : List<MovieInfoViewModel>
) : Serializable

class MovieInfoViewModel(val image: String, val actorName: String, val roleName: String) : Serializable