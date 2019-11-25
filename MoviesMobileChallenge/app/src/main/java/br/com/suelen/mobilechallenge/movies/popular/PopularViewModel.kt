package br.com.suelen.mobilechallenge.movies.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.suelen.mobilechallenge.data.model.Movie
import br.com.suelen.mobilechallenge.data.MoviesRepository
import br.com.suelen.mobilechallenge.data.model.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val moviesRepository : MoviesRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>().apply { value = emptyList() }
    val movies : LiveData<List<Movie>> = _movies

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private var currentPage : Int = 1
    private var movieList : MutableList<Movie> = mutableListOf()

    fun getPopularMovies() {

        currentPage = 1
        movieList.clear()

        _isLoading.value = true

        viewModelScope.launch {
            val result = moviesRepository.getPopularMovies(currentPage)

            if(result is Result.Success) {
                _movies.value = result.data
                movieList.addAll(result.data)
            } else {
                _error.value = (result as Result.Error).message
            }

            _isLoading.value = false
        }
    }

    fun loadMoreMovies() {
        currentPage++

        viewModelScope.launch {
            val result = moviesRepository.getPopularMovies(currentPage)

            if(result is Result.Success) {
                val newList = mutableListOf<Movie>()
                newList.addAll(movieList)
                newList.addAll(result.data)

                movieList = newList

                _movies.value = newList
            }

            _isLoading.value = false
        }
    }

}