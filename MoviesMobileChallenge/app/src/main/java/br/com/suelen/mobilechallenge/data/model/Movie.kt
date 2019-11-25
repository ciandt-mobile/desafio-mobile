package br.com.suelen.mobilechallenge.data.model

data class Movie(
    var id : Int,
    var title : String?,
    var release_date : String?,
    var runtime : Int,
    var genres : List<Genre>?,
    var overview : String?,
    var poster_path : String?,
    var backdrop_path : String?)