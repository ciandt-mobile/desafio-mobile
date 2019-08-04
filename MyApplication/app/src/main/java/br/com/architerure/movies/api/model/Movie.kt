package br.com.architerure.movies.api.model

import java.io.Serializable

data class Movie (
  val id : Int,
  val imdbId : String,
  val title : String,
  val poster_path : String,
  val backdrop_path : String,
  val overview : String,
  val release_date : String,
  val genre_ids: List<Int>
) : Serializable
