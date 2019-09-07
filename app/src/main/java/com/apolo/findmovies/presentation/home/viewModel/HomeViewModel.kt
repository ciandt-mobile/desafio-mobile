package com.apolo.findmovies.presentation.home.viewModel

import androidx.lifecycle.LiveData
import com.apolo.findmovies.data.model.BaseViewModel
import com.apolo.findmovies.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesRepository: MoviesRepository) : BaseViewModel() {

    fun onViewReady() {
        getUpcomingMovies()
    }


    private fun getUpcomingMovies() = jobs add launch(Dispatchers.IO) {
        moviesRepository.getUpcominMovies()

    }

}