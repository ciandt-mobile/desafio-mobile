package com.apolo.findmovies.presentation.movieDetail.viewModel

import androidx.lifecycle.LiveData
import com.apolo.findmovies.base.resources.LiveDataResource
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.BaseViewModel
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(val repository: MoviesRepository) : BaseViewModel() {

    private val genresLiveData = LiveDataResource<String>()

    fun onViewReady(movie: MovieViewModel) {
        getGenres(movie.genreIds)
    }

    private fun getGenres(genresIds: List<Int>) = jobs add launch(Dispatchers.IO) {

        repository.getGenres()?.let { genresList ->


            val movieGenres = mutableListOf<String>()

            genresList.genres.find { localGenres ->

                genresIds.forEach {
                    if (localGenres.id == it) {
                        movieGenres.add(localGenres.name)
                    }
                }
                false
            }

            genresLiveData.postValue(Resource.success(movieGenres.joinToString { it }))
        }
    }

    fun getGenresLiveData() = genresLiveData as LiveData<Resource<String>>
}