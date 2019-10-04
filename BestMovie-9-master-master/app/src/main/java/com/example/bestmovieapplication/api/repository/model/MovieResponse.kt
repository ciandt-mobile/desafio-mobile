package com.example.bestmovieapplication.api.repository.model

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


// Just to retrive the movies list from the request @get.
data class MovieResponse(
    val results: List<Movie>,
    val result: Movie
)

interface MovieApi{
    //Get popular movies list as a response.
    @GET("movie/popular")
    fun getPopularMovies(): Deferred<Response<MovieResponse>>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id:Int): Deferred<Response<Movie>>

}