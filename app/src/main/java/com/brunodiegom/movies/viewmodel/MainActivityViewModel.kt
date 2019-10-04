package com.brunodiegom.movies.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.model.Movie
import com.brunodiegom.movies.model.upcoming.UpcomingDataFactory
import io.reactivex.disposables.CompositeDisposable

/**
 * [AndroidViewModel] used to provide the content to the view.
 */
class MainActivityViewModel(movieRepository: MovieRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private var upcomingDataFactory = UpcomingDataFactory(disposable, movieRepository)

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setEnablePlaceholders(false)
        .build()

    /**
     * Provides a [Movie] list to be presented at the View.
     */
    val movies: LiveData<PagedList<Movie>> by lazy { LivePagedListBuilder(upcomingDataFactory, config).build() }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    companion object {
        private const val PAGE_SIZE = 15
    }
}
