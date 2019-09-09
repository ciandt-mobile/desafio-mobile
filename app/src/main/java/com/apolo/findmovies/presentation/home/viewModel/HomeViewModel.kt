package com.apolo.findmovies.presentation.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import com.apolo.findmovies.base.connection.ConnectionUseCase
import com.apolo.findmovies.base.resources.LiveDataResource
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.BaseViewModel
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.repository.MoviesRepository

class HomeViewModel(private val moviesRepository: MoviesRepository) : BaseViewModel() {

    private val moviesLivedata = LiveDataResource<List<MovieViewModel>>()

    fun onViewReady() {
        getUpcomingMovies()
        getGenres()
    }

    fun onCategoryChange(isUpcoming: Boolean) {
        if (isUpcoming) {
            getUpcomingMovies()
        } else {
            getPopularMovies()
        }
    }

    private fun getUpcomingMovies() = runCoroutine {
        ConnectionUseCase.testInternetConnection()
        moviesLivedata.postValue(Resource.loading())

        moviesRepository.getUpcomingMovies()?.let { moviesResponse ->

            if (moviesResponse.movies.isNotEmpty()) {
                //TODO: Need to return pages to do pagination
                moviesLivedata.postValue(Resource.success(moviesResponse.toViewModelList()))
            }
        }
    }.onError {
        Log.d("","")
    }

    private fun getPopularMovies() = runCoroutine {
        ConnectionUseCase.testInternetConnection()
        moviesLivedata.postValue(Resource.loading())

        moviesRepository.getPopularMovies()?.let { moviesResponse ->

            if (moviesResponse.movies.isNotEmpty()) {
                //TODO: Need to return pages to do pagination
                moviesLivedata.postValue(Resource.success(moviesResponse.toViewModelList()))
            }
        }
    }.onError {
        Log.d("","")
    }

    private fun getGenres() = runCoroutine {
        ConnectionUseCase.testInternetConnection()
        moviesRepository.getGenres()
    }.onError {
        Log.d("","")
    }

    fun getMoviesLiveData() = moviesLivedata as LiveData<Resource<List<MovieViewModel>>>

}