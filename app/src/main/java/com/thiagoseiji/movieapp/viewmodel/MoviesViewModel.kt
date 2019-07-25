package com.thiagoseiji.movieapp.viewmodel

import androidx.lifecycle.LiveData
import com.thiagoseiji.movieapp.data.api.MovieResponse
import com.thiagoseiji.movieapp.repository.MovieRepository

class MoviesViewModel(private val repository: MovieRepository): BaseViewModel<MovieResponse>() {

    private lateinit var moviesList: LiveData<MovieResponse>

    override fun getData(): LiveData<MovieResponse> {
        moviesList = repository.loadData()
        return moviesList
    }

    override fun saveToDatabase(data: MovieResponse) {
    }



}