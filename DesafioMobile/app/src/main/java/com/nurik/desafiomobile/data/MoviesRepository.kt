package com.nurik.desafiomobile.data

class MoviesRepository(private val api: MoviesApi)
    : SafeApiRequest() {

    val API_KEY = "5d702d017fd75aaae63d08759fb991f0"

    suspend fun getPopularMovies() = apiRequest { api.getPopularMovies(API_KEY)}

    suspend fun getUpcomingMovies() = apiRequest { api.getUpcomingMovies(API_KEY)}

    suspend fun getGenreMovies() = apiRequest { api.getGenreMovies(API_KEY)}

    suspend fun getMovieDetail(id: Int) = apiRequest { api.getMovieDetail( id, API_KEY, "credits")}
}