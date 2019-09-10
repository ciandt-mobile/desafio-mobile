package com.apolo.findmovies.presentation.movieDetail.viewModel

import androidx.lifecycle.LiveData
import com.apolo.findmovies.base.connection.ConnectionUseCase
import com.apolo.findmovies.base.resources.LiveDataResource
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.BaseViewModel
import com.apolo.findmovies.data.model.MovieCreditsViewModel
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.repository.MoviesRepository
import com.apolo.findmovies.repository.UseCaseErrorCode

class MovieDetailViewModel(private val moviesRepository: MoviesRepository) : BaseViewModel() {

    private val genresLiveData = LiveDataResource<String>()
    private val creditsListLiveData = LiveDataResource<List<MovieCreditsViewModel>>()

    fun onViewReady(movie: MovieViewModel) {
        getGenres(movie.genreIds)
        getCredits(movie.movieId)
    }

    private fun getGenres(genresIds: List<Int>) = runCoroutine {
        ConnectionUseCase.testInternetConnection()
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
        when(it.userCaseErrorCode) {
            UseCaseErrorCode.NO_INTERNET_CONNECTION -> {
                genresLiveData.postValue(Resource.error(errorMessage = UseCaseErrorCode.NO_INTERNET_CONNECTION.messageError))
            } else -> {
                genresLiveData.postValue(Resource.error(errorMessage = UseCaseErrorCode.UNKNOWN_ERROR.messageError))
            }
        }

    }

    fun getGenresLiveData() = genresLiveData as LiveData<Resource<String>>

    fun getCreditsLiveData() = creditsListLiveData as LiveData<Resource<List<MovieCreditsViewModel>>>

    private fun getCredits(movieId: Int) = runCoroutine {
        ConnectionUseCase.testInternetConnection()
        moviesRepository.getCredits(movieId)?.let { movieCreditsResponse ->
            creditsListLiveData.postValue(Resource.success(movieCreditsResponse))
        }
    }.onError {
        when (it.userCaseErrorCode) {
            UseCaseErrorCode.NO_INTERNET_CONNECTION -> {
                genresLiveData.postValue(Resource.error(errorMessage = UseCaseErrorCode.NO_INTERNET_CONNECTION.messageError))
            }
            else -> {
                genresLiveData.postValue(Resource.error(errorMessage = UseCaseErrorCode.UNKNOWN_ERROR.messageError))
            }
        }
    }
}