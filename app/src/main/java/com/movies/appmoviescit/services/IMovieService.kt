package com.movies.appmoviescit.services

import com.movies.appmoviescit.model.CastResults
import com.movies.appmoviescit.model.MovieDetail
import com.movies.appmoviescit.model.MovieItem
import com.movies.appmoviescit.model.MovieResults
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieService {

    @GET("movie/popular?language=pt-BR")
    fun popularMovies(@Query("api_key") api_key: String,
                      @Query("language") language: String,
                      @Query("page") page: Int): Call<MovieResults>

    @GET("movie/upcoming")
    fun upcomingrMovies(@Query("api_key") api_key: String,
                        @Query("language") language: String,
                        @Query("page") page: Int): Call<MovieResults>

    @GET("movie/{movie_id}/credits")
    fun credits(@Path("movie_id") movie_id: String,
                @Query("api_key") api_key: String): Call<CastResults>

    @GET("movie/{movie_id}")
    fun movieDetail(@Path("movie_id") movie_id: String,
                    @Query("api_key") api_key: String): Call<MovieDetail>
}