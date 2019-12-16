package com.nurik.desafiomobile.ui.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nurik.desafiomobile.data.MoviesRepository
import com.nurik.desafiomobile.pojo.Movie
import com.nurik.desafiomobile.utils.Coroutines
import kotlinx.coroutines.Job

class MovieListViewModel(private val repository: MoviesRepository) : ViewModel(){

    private lateinit var job: Job
    private val mPopularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>>
        get() = mPopularMovies

    private val mUpcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>>
        get() = mUpcomingMovies

    private val mMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = mMovie

    fun getPopularMovies(){
        job = Coroutines.ioThenMain(
                {repository.getPopularMovies()},
                { mPopularMovies.value = it?.results })
    }

    fun getUpcomingMovies(){
        job = Coroutines.ioThenMain(
                {repository.getUpcomingMovies()},
                { mUpcomingMovies.value = it?.results })
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
