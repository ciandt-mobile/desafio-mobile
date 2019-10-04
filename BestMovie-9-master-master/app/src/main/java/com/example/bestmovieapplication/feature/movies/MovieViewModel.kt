package com.example.bestmovieapplication.feature.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bestmovieapplication.api.repository.Apifactory
import com.example.bestmovieapplication.api.repository.model.Movie
import com.example.bestmovieapplication.api.repository.repository.IMovieRepository
import com.example.bestmovieapplication.api.repository.repository.MovieRepository
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class MovieViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    lateinit var repository: IMovieRepository


    val popularMoviesLiveData = MutableLiveData<MutableList<Movie>>()

    val specificMovieLiveData = MutableLiveData<Movie>()

    fun fetchMovies() {
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            popularMoviesLiveData.postValue(popularMovies)
        }
    }

    fun fetchMovie(id: Int) {
        scope.launch {
            val specificMovie = repository.getMovie(id)
            specificMovieLiveData.postValue(specificMovie)
        }
    }

    fun latestMovies(movieList :MutableList<Movie>): MutableList<Movie> {

        movieList.sortByDescending {

            val releaseDateFormat: Calendar = Calendar.getInstance()
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            releaseDateFormat.time = simpleDateFormat.parse(it.release_date)
            releaseDateFormat
        }
        return movieList
    }
}