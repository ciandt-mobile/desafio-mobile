package com.apolo.findmovies.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("movie/upcoming")
    fun getUpcomingMovies() : Call<Unit>

}