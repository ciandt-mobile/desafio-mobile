package br.com.victoroliveira.cit.presentation.upcoming

import androidx.lifecycle.MutableLiveData
import br.com.victoroliveira.cit.data.model.Movie
import br.com.victoroliveira.cit.domain.MovieRepository
import br.com.victoroliveira.cit.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class UpcomingViewModel(private val upcomingRepository: MovieRepository) : BaseViewModel() {
    var page: Int = 1
    private var listMovieHelper: MutableList<Movie> = mutableListOf()

    val upcomingList: MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getUpcoming() {
        isLoading.value = true
        scope.launch {
            try {
                val response = upcomingRepository.getUpcoming(
                    page,
                    Locale.getDefault().language + "-" + Locale.getDefault().country
                )
                listMovieHelper.addAll(response.results)
                upcomingList.value = listMovieHelper
                errorConnection.value = false
            } catch (e: Exception) {
                errorMessage.value = getError(e).getErrorMessage()
                errorConnection.value = true
            }
            isLoading.value = false
        }
    }
}