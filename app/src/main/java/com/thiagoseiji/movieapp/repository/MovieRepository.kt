package com.thiagoseiji.movieapp.repository

import androidx.lifecycle.MutableLiveData
import com.thiagoseiji.movieapp.data.Movie
import com.thiagoseiji.movieapp.data.api.MovieResponse
import com.thiagoseiji.movieapp.retrofit.MovieService

class MovieRepository(service: MovieService) : BaseRepository<MovieResponse>(service) {

    fun loadPopularMovies(): MutableLiveData<MovieResponse> {
       return fetchData { service.getPopularMoviesAsync() }
    }

    fun loadUpcomingMovies(): MutableLiveData<MovieResponse> {
        return fetchData { service.getUpcomingMoviesAsync() }
    }

    fun loadMovie(id: Int): MutableLiveData<Movie> {
        return fetchData { service.getMovieById(id) }
    }


}