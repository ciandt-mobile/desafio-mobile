package br.com.suelen.mobilechallenge.data

import br.com.suelen.mobilechallenge.data.model.Movie
import br.com.suelen.mobilechallenge.data.model.Person
import br.com.suelen.mobilechallenge.data.model.Result
import br.com.suelen.mobilechallenge.di.ApplicationModule.RemoteMoviesDataSource
import br.com.suelen.mobilechallenge.utilities.wrapEspressoIdlingResource
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    @RemoteMoviesDataSource private val remoteMoviesDataSource: MoviesDataSource
) : MoviesRepository {

    override suspend fun getUpcomingMovies(page : Int): Result<List<Movie>> {
        wrapEspressoIdlingResource {
            return remoteMoviesDataSource.getUpcomingMovies(page)
        }
    }

    override suspend fun getPopularMovies(page : Int): Result<List<Movie>> {
        wrapEspressoIdlingResource {
            return remoteMoviesDataSource.getPopularMovies(page)
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Result<Movie> {
        wrapEspressoIdlingResource {
            return remoteMoviesDataSource.getMovieDetail(movieId)
        }
    }

    override suspend fun getMovieCast(movieId: Int): Result<List<Person>> {
        wrapEspressoIdlingResource {
            return remoteMoviesDataSource.getMovieCast(movieId)
        }
    }
}