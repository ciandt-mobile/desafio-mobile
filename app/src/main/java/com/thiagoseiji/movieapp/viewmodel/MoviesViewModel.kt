package com.thiagoseiji.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiagoseiji.movieapp.data.api.MovieResponse
import com.thiagoseiji.movieapp.repository.MovieRepository

class MoviesViewModel(private val repository: MovieRepository): ViewModel() {

    private lateinit var moviesList: MutableLiveData<MovieResponse>

    fun getPopularMovies(): LiveData<MovieResponse> {
        moviesList = repository.loadPopularMovies()
        return moviesList
    }

    fun getUpcomingMovies(): LiveData<MovieResponse> {
        moviesList = repository.loadUpcomingMovies()
        return moviesList

    }

}