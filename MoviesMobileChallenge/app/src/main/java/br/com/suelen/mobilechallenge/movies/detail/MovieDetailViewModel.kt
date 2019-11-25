package br.com.suelen.mobilechallenge.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.suelen.mobilechallenge.data.model.Movie
import br.com.suelen.mobilechallenge.data.MoviesRepository
import br.com.suelen.mobilechallenge.data.model.Person
import br.com.suelen.mobilechallenge.data.model.Result
import br.com.suelen.mobilechallenge.utilities.DateTimeUtil
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val moviesRepository : MoviesRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie : LiveData<Movie> = _movie

    private val _movieInfo = MutableLiveData<String>()
    val movieInfo : LiveData<String> = _movieInfo

    private val _castList = MutableLiveData<List<Person>>()
    val castList : LiveData<List<Person>> = _castList

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    private val _castError = MutableLiveData<String>()
    val castError : LiveData<String> = _castError

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun setup(movieId : Int) {

        _isLoading.value = true
        viewModelScope.launch {
            val result = moviesRepository.getMovieDetail(movieId)

            if(result is Result.Success) {
                _movie.value = result.data
                setupMovieInfo(result.data)
            } else {
                _error.value = (result as Result.Error).message
            }

            _isLoading.value = false
        }

        viewModelScope.launch {
            val result = moviesRepository.getMovieCast(movieId)

            if(result is Result.Success) {
                _castList.value = result.data
            } else {
                _castError.value = (result as Result.Error).message
            }
        }
    }

    private fun setupMovieInfo(movie : Movie) {
        val movieInfoStringBuilder = StringBuilder()

        movie.genres?.apply {
            for ((index, genre) in withIndex()) {

                movieInfoStringBuilder.append(genre.name)

                if(index < size-1) {
                    movieInfoStringBuilder.append(", ")
                }
            }
        }

        movie.release_date?.apply {
            movieInfoStringBuilder.append(" | ${getReleaseYear(this)}")
        }


        movieInfoStringBuilder.append(" | ${movie.runtime}m")

        _movieInfo.value = movieInfoStringBuilder.toString()
    }

    private fun getReleaseYear(releaseDateString : String) : Int {
        val releaseDate = DateTimeUtil.parseDate(releaseDateString)

        var releaseYear = 0

        releaseDate?.apply {
            val calendarDate = Calendar.getInstance()
            calendarDate.time = releaseDate
            releaseYear = calendarDate.get(Calendar.YEAR)
        }

        return releaseYear
    }



}