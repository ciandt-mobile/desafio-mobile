package com.msaviczki.themovieapp.presentation.home.viewstate

import com.msaviczki.themovieapp.data.MovieMap

data class ViewState(
    val error: String = "",
    val loading: Boolean = false,
    val response: MutableList<MovieMap> = mutableListOf()
)