package com.movies.appmoviescit.model

data class MovieDetail(val title: String,
                       val backdrop_path: String,
                       val genres: List<Genre>,
                       val overview: String,
                       val release_date: String,
                       val runtime: String)