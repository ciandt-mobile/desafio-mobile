package com.msaviczki.themovieapp.presentation.movie.viewstate

import com.msaviczki.themovieapp.data.MovieMap

data class MovieViewState(
    val error: String = "",
    val loading: Boolean = false,
    val response: MutableList<MovieMap> = mutableListOf()
)