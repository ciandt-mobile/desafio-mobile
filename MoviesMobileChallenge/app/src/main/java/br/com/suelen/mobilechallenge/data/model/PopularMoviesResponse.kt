package br.com.suelen.mobilechallenge.data.model

data class PopularMoviesResponse(
    var page : Int,
    var total_results : Int,
    var total_pages : Int,
    var results : List<Movie>?
)