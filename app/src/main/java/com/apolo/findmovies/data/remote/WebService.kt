package com.apolo.findmovies.data.remote

import com.apolo.findmovies.repository.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("movie/upcoming")
    fun getUpcomingMovies() : Call<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies() : Call<Unit>

    //TODO: To populate the generes of movie detail
    @GET("genre/movie/list")
    fun getMoviesGenders() : Call<Unit>

    //TODO: To populate movie detail adapter
    @GET("/movie/{movie_id}/credits")
    fun getCredits() : Call<Unit>

}