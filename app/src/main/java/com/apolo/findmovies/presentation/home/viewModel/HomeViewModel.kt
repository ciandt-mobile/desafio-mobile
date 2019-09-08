package com.apolo.findmovies.presentation.home.viewModel

import androidx.lifecycle.LiveData
import com.apolo.findmovies.base.resources.LiveDataResource
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.BaseViewModel
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesRepository: MoviesRepository) : BaseViewModel() {


    private val moviesLivedata = LiveDataResource<List<MovieViewModel>>()

    fun onViewReady() {
        getUpcomingMovies()
    }


    private fun getUpcomingMovies() = jobs add launch(Dispatchers.IO) {
        moviesLivedata.postValue(Resource.loading())

        moviesRepository.getUpcomingMovies()?.let { moviesResponse ->

            if (moviesResponse.movies.isNotEmpty()) {
                //TODO: Need to return pages to do pagination
                moviesLivedata.postValue(Resource.success(moviesResponse.toViewModelList()))
            }

        }

    }

    fun getMoviesLiveData() = moviesLivedata as LiveData<Resource<List<MovieViewModel>>>

}