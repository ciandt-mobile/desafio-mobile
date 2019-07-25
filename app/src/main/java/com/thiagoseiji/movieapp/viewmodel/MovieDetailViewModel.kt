package com.thiagoseiji.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiagoseiji.movieapp.data.Movie
import com.thiagoseiji.movieapp.repository.MovieRepository

class MovieDetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private lateinit var moviesList: MutableLiveData<Movie>

    fun getMovie(id: Int): LiveData<Movie> {
        moviesList = movieRepository.loadMovie(id)
        return moviesList
    }
}