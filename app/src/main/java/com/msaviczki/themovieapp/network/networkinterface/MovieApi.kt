package com.msaviczki.themovieapp.network.networkinterface

import com.msaviczki.themovieapp.data.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/popular")
    fun getPopularMovies() : Deferred<MovieListResponse>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id:Int)
}