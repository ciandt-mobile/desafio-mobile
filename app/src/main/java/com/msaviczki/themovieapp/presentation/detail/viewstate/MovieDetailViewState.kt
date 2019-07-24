package com.msaviczki.themovieapp.presentation.detail.viewstate

import com.msaviczki.themovieapp.data.MovieDetailMap

data class MovieDetailViewState(
    val error: String = "",
    val loading: Boolean = false,
    val response: MovieDetailMap?
)