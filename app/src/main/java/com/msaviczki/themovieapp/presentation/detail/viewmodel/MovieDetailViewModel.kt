package com.msaviczki.themovieapp.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContext
import com.msaviczki.themovieapp.presentation.detail.viewstate.MovieDetailViewState
import com.msaviczki.themovieapp.network.core.Result.Success
import com.msaviczki.themovieapp.network.core.Result.Error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface MovieDetailViewModelImpl {
    fun getMovieDetail(id: Int)
}

class MovieDetailViewModel(
    private val repository: MovieDetailViewModelRepository,
    dispatcher: CoroutineBaseContext
) : ViewModel(), MovieDetailViewModelImpl {

    private val movieJob = Job()

    private val coroutineContext: CoroutineContext = movieJob + dispatcher.unconfined()

    private val coroutineScope = CoroutineScope(coroutineContext)

    private val movieLiveData: MutableLiveData<MovieDetailViewState> = MutableLiveData()

    init {
        movieLiveData.value = MovieDetailViewState(response = null)
    }

    override fun getMovieDetail(id: Int) {
        setValue(MovieDetailViewState(loading = true, response = null))
        coroutineScope.launch {
            val response = repository.getMovieById(id)

            when (response) {
                is Success -> {
                    postValue(MovieDetailViewState(loading = false, response = response.data))
                }
                is Error -> {
                    postValue(MovieDetailViewState(loading = false, error = response.error, response = null))
                }
            }
        }
    }

    private fun postValue(stateMovie: MovieDetailViewState) {
        movieLiveData.postValue(
            movieLiveData.value!!.copy(
                loading = stateMovie.loading,
                error = stateMovie.error,
                response = stateMovie.response
            )
        )
    }

    private fun setValue(stateMovie: MovieDetailViewState) {
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

    fun getMovieLiveData(): LiveData<MovieDetailViewState> = movieLiveData

}