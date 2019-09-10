package com.apolo.findmovies.data.remote

import com.apolo.findmovies.repository.model.GenresResponse
import com.apolo.findmovies.repository.model.MovieCreditsResponse
import com.apolo.findmovies.repository.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") nextPage : Int) : Call<MoviesResponse?>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") nextPage : Int) : Call<MoviesResponse?>

    @GET("genre/movie/list")
    fun getMoviesGenres() : Call<GenresResponse?>

    @GET("movie/{movie_id}/credits")
    fun getCredits(@Path("movie_id") movieId : Int) : Call<MovieCreditsResponse?>

    //TODO: To get configurations and base urls to laod images.
    @GET("configuration")
    fun getConfiguration() : Call<Unit>

}