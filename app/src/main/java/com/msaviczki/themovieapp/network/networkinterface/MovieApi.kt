package com.msaviczki.themovieapp.network.networkinterface

import com.msaviczki.themovieapp.data.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page : Int
    ) : Deferred<MovieListResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page : Int
    ) : Deferred<MovieListResponse>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id:Int)
}