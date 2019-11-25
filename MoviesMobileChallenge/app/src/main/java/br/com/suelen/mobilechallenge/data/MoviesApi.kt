package br.com.suelen.mobilechallenge.data

import br.com.suelen.mobilechallenge.data.model.Movie
import br.com.suelen.mobilechallenge.data.model.MovieCastResponse
import br.com.suelen.mobilechallenge.data.model.PopularMoviesResponse
import br.com.suelen.mobilechallenge.data.model.UpcomingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "064f1171da97b776d304da37f421fa29",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): UpcomingMoviesResponse?


    @GET("discover/movie")
    suspend fun getPopularMovies (
        @Query("api_key") apiKey: String = "064f1171da97b776d304da37f421fa29",
        @Query("sort_by") sortBy : String = "popularity.desc",
        @Query("page") page: Int = 1
    ) : PopularMoviesResponse?

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId : Int,
        @Query("api_key") apiKey: String = "064f1171da97b776d304da37f421fa29",
        @Query("language") language: String = "en-US"
    ) : Movie?

    @GET("movie/{id}/credits")
    suspend fun getMovieCast(
        @Path("id") movieId : Int,
        @Query("api_key") apiKey: String = "064f1171da97b776d304da37f421fa29",
        @Query("language") language: String = "en-US"
    ) : MovieCastResponse?

}