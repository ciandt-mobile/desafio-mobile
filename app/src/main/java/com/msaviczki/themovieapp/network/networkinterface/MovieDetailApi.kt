package com.msaviczki.themovieapp.network.networkinterface

import com.msaviczki.themovieapp.data.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailApi {
    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: Int, @Query("api_key") apiKey: String): Deferred<MovieResponse?>
}