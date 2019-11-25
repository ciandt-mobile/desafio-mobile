package br.com.suelen.mobilechallenge.data

import br.com.suelen.mobilechallenge.data.model.Movie
import br.com.suelen.mobilechallenge.data.model.Person
import br.com.suelen.mobilechallenge.data.model.Result

interface MoviesDataSource {

    suspend fun getUpcomingMovies(page : Int) : Result<List<Movie>>

    suspend fun getPopularMovies(page : Int) : Result<List<Movie>>

    suspend fun getMovieDetail(movieId : Int) : Result<Movie>

    suspend fun getMovieCast(movieId: Int) : Result<List<Person>>
}