package com.msaviczki.themovieapp.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaviczki.themovieapp.network.core.Result
import com.msaviczki.themovieapp.presentation.home.viewstate.ViewState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface HomeViewModelImpl {
    fun requestMovies()
}

class HomeViewModel(private val repository: HomeViewModelRepository) : ViewModel(), HomeViewModelImpl {

    private val movieJob = Job()

    private val coroutineScope = CoroutineScope(coroutineContext)

    private val coroutineContext: CoroutineContext get() = movieJob + Dispatchers.Default

    private val movieLiveData: MutableLiveData<ViewState> = MutableLiveData()

    init {
        movieLiveData.value = ViewState()
    }

    override fun requestMovies() {
        setValue(ViewState(loading = true))
        coroutineScope.launch {
            val response = repository.requestMovies()

            when (response) {
                is Result.Success -> {
                    postValue(ViewState(loading = false))
                }
                is Result.Error -> {
                    postValue(ViewState(loading = false, error = response.error))
                }
            }
        }
    }

    private fun postValue(state: ViewState) {
        movieLiveData.postValue(
            movieLiveData.value!!.copy(
                loading = state.loading,
                error = state.error
            )
        )
    }

    private fun setValue(state: ViewState) {
        movieLiveData.value = movieLiveData.value!!.copy(
            loading = state.loading,
            error = state.error
        )
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    fun getMovieLiveData(): LiveData<ViewState> = movieLiveData
}