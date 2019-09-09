package com.apolo.findmovies.presentation.movieDetail.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import com.apolo.findmovies.base.resources.LiveDataResource
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.BaseViewModel
import com.apolo.findmovies.data.model.MovieCreditsViewModel
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.repository.MoviesRepository

class MovieDetailViewModel(val moviesRepository: MoviesRepository) : BaseViewModel() {

    private val genresLiveData = LiveDataResource<String>()
    private val creditsListLiveData = LiveDataResource<List<MovieCreditsViewModel>>()


    fun onViewReady(movie: MovieViewModel) {
        getGenres(movie.genreIds)
        getCredits(movie.movieId)
    }

    private fun getGenres(genresIds: List<Int>) = runCoroutine {

        moviesRepository.getGenres()?.let { genresList ->

            val movieGenres = mutableListOf<String>()

            genresList.genres.find { genre ->

                genresIds.forEach {
                    if (genre.id == it) {
                        movieGenres.add(genre.name)
                    }
                }
                false
            }

            genresLiveData.postValue(Resource.success(movieGenres.joinToString { it }))
        }
    }.onError {
        Log.d("","")
    }

    fun getGenresLiveData() = genresLiveData as LiveData<Resource<String>>

    fun getCreditsLiveData() = creditsListLiveData as LiveData<Resource<List<MovieCreditsViewModel>>>

    private fun getCredits(movieId: Int) = runCoroutine {
        moviesRepository.getCredits(movieId)?.let { movieCreditsResponse ->
            creditsListLiveData.postValue(Resource.success(movieCreditsResponse))
        }
    }.onError {
        Log.d("","")
    }
}