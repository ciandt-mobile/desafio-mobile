package br.com.victoroliveira.cit.presentation.popular

import androidx.lifecycle.MutableLiveData
import br.com.victoroliveira.cit.data.model.Movie
import br.com.victoroliveira.cit.domain.MovieRepository
import br.com.victoroliveira.cit.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class PopularViewModel(private val popularRepository: MovieRepository) : BaseViewModel() {
    var page: Int = 1
    private var listPopularHelper: MutableList<Movie> = mutableListOf()

    val listPopular: MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getPopular() {
        isLoading.value = true
        scope.launch {
            try {
                val response = popularRepository.getPopular(page, Locale.getDefault().language + "-" + Locale.getDefault().country)
                listPopularHelper.addAll(response.results)
                listPopular.value = listPopularHelper
                errorConnection.value = false
            } catch (e: Exception) {
                errorMessage.value = getError(e).getErrorMessage()
                errorConnection.value = true
            }
            isLoading.value = false
        }
    }
}