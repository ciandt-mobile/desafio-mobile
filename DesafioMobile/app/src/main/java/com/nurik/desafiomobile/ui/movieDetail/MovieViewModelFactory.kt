package com.nurik.desafiomobile.ui.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nurik.desafiomobile.data.MoviesRepository

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
        private val repository: MoviesRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}