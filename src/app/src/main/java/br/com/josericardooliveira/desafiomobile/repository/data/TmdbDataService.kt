package br.com.josericardooliveira.desafiomobile.repository.data

import br.com.josericardooliveira.desafiomobile.model.CastQuery
import br.com.josericardooliveira.desafiomobile.model.Movie
import br.com.josericardooliveira.desafiomobile.model.MovieQuery
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbDataService {

    @GET("movie/popular")
    fun popularMovies(@Query("page") page: Int): Call<MovieQuery>

    @GET("movie/upcoming")
    fun upcomingMovies(@Query("page") page: Int): Call<MovieQuery>

    @GET("movie/{id}")
    fun movieDetails(@Path("id") id: Int): Call<Movie>

    @GET("movie/{id}/credits")
    fun movieCast(@Path("id") id: Int): Call<CastQuery>
}