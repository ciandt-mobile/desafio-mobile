package br.com.suelen.mobilechallenge.data

import br.com.suelen.mobilechallenge.data.model.Result.Success
import br.com.suelen.mobilechallenge.data.model.Movie
import br.com.suelen.mobilechallenge.data.model.Person
import br.com.suelen.mobilechallenge.data.model.Result
import br.com.suelen.mobilechallenge.utilities.NetworkUtil

class RemoteMoviesDataSource internal constructor(
    private val moviesApi : MoviesApi
) : MoviesDataSource {

    override suspend fun getUpcomingMovies(page : Int) : Result<List<Movie>> {
        try {
            val results = moviesApi.getUpcomingMovies(page = page)

            results?.results?.apply {
                return Success(this)
            }
        } catch (e: Exception) {
            return NetworkUtil.parseRetrofitException(e)
        }

        return Result.Error("Unknown error")
    }

    override suspend fun getPopularMovies(page : Int): Result<List<Movie>> {
        try {
            val results = moviesApi.getPopularMovies(page = page)
            results?.results?.apply {
                return Success(this)
            }
        } catch (e : Exception) {
            return NetworkUtil.parseRetrofitException(e)
        }
        return Result.Error("Unknown error")
    }

    override suspend fun getMovieDetail(movieId: Int): Result<Movie> {
        try {
            val result = moviesApi.getMovieDetail(movieId)
            result?.apply {
                return Success(this)
            }
        } catch (e : Exception) {
            return NetworkUtil.parseRetrofitException(e)
        }
        return Result.Error("Unknown error")
    }

    override suspend fun getMovieCast(movieId: Int): Result<List<Person>> {
        try {
            val result = moviesApi.getMovieCast(movieId)
            result?.cast?.apply {
                return Success(this)
            }
        } catch (e : java.lang.Exception) {
            return NetworkUtil.parseRetrofitException(e)
        }
        return Result.Error("Unknown error")
    }

}