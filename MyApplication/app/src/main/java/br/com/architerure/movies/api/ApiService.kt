package br.com.architerure.movies.api

import br.com.architerure.movies.api.model.CastResponse
import br.com.architerure.movies.api.model.GenreResponse
import br.com.architerure.movies.api.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //https://api.themoviedb.org/3/movie/popular?api_key=3fa9058382669f72dcb18fb405b7a831&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/upcoming?api_key=3fa9058382669f72dcb18fb405b7a831&language=en-US&page=1
    //http://api.themoviedb.org/3/genre/movie/list?api_key=3fa9058382669f72dcb18fb405b7a831

    @GET("/3/movie/popular")
    fun popularMovies(
        @Query("api_key") apiKey : String,
        @Query("page") page : String,
        @Query("language") language: String) : Call<MoviesResponse>

    @GET("/3/movie/{movie_id}/credits")
    fun castMovie(
        @Path("movie_id") moveId: Int,
        @Query("api_key") apiKey : String
    ) : Call<CastResponse>

    @GET("/3/movie/upcoming")
    fun upComingMovies(
        @Query("api_key") apiKey : String,
        @Query("page") page : String,
        @Query("language") language: String) : Call<MoviesResponse>

    @GET("/3/genre/movie/list")
    fun genres(
        @Query("api_key") apiKey : String) : Call<GenreResponse>

}