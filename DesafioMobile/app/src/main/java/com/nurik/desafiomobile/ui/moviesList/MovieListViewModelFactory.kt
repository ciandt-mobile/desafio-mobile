package com.nurik.desafiomobile.ui.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nurik.desafiomobile.data.MoviesRepository

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(
        private val repository: MoviesRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(repository) as T
    }
}