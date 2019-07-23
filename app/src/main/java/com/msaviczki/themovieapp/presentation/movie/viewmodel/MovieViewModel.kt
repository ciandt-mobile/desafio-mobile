package com.msaviczki.themovieapp.presentation.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContext
import com.msaviczki.themovieapp.helper.customview.Toggle
import com.msaviczki.themovieapp.network.core.Result
import com.msaviczki.themovieapp.presentation.movie.viewstate.MovieViewState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface MovieViewModelImpl {
    fun requestPopularMovies()
    fun requestUpComingMovies()
}

class MovieViewModel(
    private val repository: MovieViewModelRepository,
    dispatcher: CoroutineBaseContext
) : ViewModel(), MovieViewModelImpl {

    private val movieJob = Job()

    private val coroutineContext: CoroutineContext = movieJob + dispatcher.unconfined()

    private val coroutineScope = CoroutineScope(coroutineContext)

    private val movieLiveData: MutableLiveData<MovieViewState> = MutableLiveData()

    var toggleState = Toggle.RoundSwitchState.ON_LEFT

    init {
        movieLiveData.value = MovieViewState()
    }

    override fun requestPopularMovies() {
        setValue(MovieViewState(loading = true))
        coroutineScope.launch {
            val response = repository.requestPopularMovies()

            when (response) {
                is Result.Success -> {
                    postValue(MovieViewState(loading = false, response = response.data))
                }
                is Result.Error -> {
                    postValue(MovieViewState(loading = false, error = response.error))
                }
            }
        }
    }

    override fun requestUpComingMovies() {
        setValue(MovieViewState(loading = true))
        coroutineScope.launch {
            val response = repository.requestUpcomingMovies()

            when (response) {
                is Result.Success -> {
                    postValue(MovieViewState(loading = false, response = response.data))
                }
                is Result.Error -> {
                    postValue(MovieViewState(loading = false, error = response.error))
                }
            }
        }
    }

    private fun postValue(stateMovie: MovieViewState) {
        movieLiveData.postValue(
            movieLiveData.value!!.copy(
                loading = stateMovie.loading,
                error = stateMovie.error,
                response = stateMovie.response
            )
        )
    }

    private fun setValue(stateMovie: MovieViewState) {
        movieLiveData.value = movieLiveData.value!!.copy(
            loading = stateMovie.loading,
            error = stateMovie.error,
            response = stateMovie.response
        )
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    fun getMovieLiveData(): LiveData<MovieViewState> = movieLiveData
}