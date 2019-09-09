package com.apolo.findmovies.presentation.movieDetail.viewModel

import androidx.lifecycle.LiveData
import com.apolo.findmovies.base.resources.LiveDataResource
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.BaseViewModel
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(val moviesRepository: MoviesRepository) : BaseViewModel() {

    private val genresLiveData = LiveDataResource<String>()

    fun onViewReady(movie: MovieViewModel) {
        getGenres(movie.genreIds)
        getCredits(movie.movieId)
    }

    private fun getGenres(genresIds: List<Int>) = jobs add launch(Dispatchers.IO) {

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
    }

    fun getGenresLiveData() = genresLiveData as LiveData<Resource<String>>


    private fun getCredits(movieId: Int) = jobs add launch(Dispatchers.IO) {
        moviesRepository.getCredits(movieId)?.let { movieCreditsResponse ->
            movieCreditsResponse
        }
    }
}