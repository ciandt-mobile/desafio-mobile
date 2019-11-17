package br.com.victoroliveira.cit.presentation.detail

import androidx.lifecycle.MutableLiveData
import br.com.victoroliveira.cit.data.model.Cast
import br.com.victoroliveira.cit.data.model.DetailResponse
import br.com.victoroliveira.cit.data.model.Genres
import br.com.victoroliveira.cit.domain.MovieRepository
import br.com.victoroliveira.cit.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    val listCast: MutableLiveData<MutableList<Cast>> = MutableLiveData()
    val listGenre: MutableLiveData<MutableList<Genres>> = MutableLiveData()
    val runTime: MutableLiveData<Long> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getCast(id: Int) {
        isLoading.value = true
        scope.launch {
            try {
                val listCastHelper: MutableList<Cast> = mutableListOf()
                val response = movieRepository.getCast(id)
                listCastHelper.addAll(response.cast)
                listCast.value = listCastHelper
                errorConnection.value = false
            } catch (e: Exception) {
                errorMessage.value = getError(e).getErrorMessage()
                errorConnection.value = true
            }
            isLoading.value = false
        }
    }

    fun getDetail(id: Int) {
        isLoading.value = true
        scope.launch {
            try {
                val listGenreHelper: MutableList<Genres> = mutableListOf()
                val response = movieRepository.getDetail(id)
                listGenreHelper.addAll(response.genres)
                listGenre.value = listGenreHelper
                runTime.value = response.runtime
                errorConnection.value = false
            } catch (e: Exception) {
                errorMessage.value = getError(e).getErrorMessage()
                errorConnection.value = true
            }
            isLoading.value = false
        }
    }
}