package com.brunodiegom.movies.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.brunodiegom.movies.R
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.model.Movie
import com.brunodiegom.movies.model.popular.PopularDataFactory
import com.brunodiegom.movies.model.upcoming.UpcomingDataFactory
import io.reactivex.disposables.CompositeDisposable

/**
 * [AndroidViewModel] used to provide the content to the view.
 */
class MainActivityViewModel(movieRepository: MovieRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private var upcomingDataFactory = UpcomingDataFactory(disposable, movieRepository)

    private var popularDataFactory = PopularDataFactory(disposable, movieRepository)

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setEnablePlaceholders(false)
        .build()

    private val upcoming: LiveData<PagedList<Movie>> by lazy {
        LivePagedListBuilder(
            upcomingDataFactory,
            config
        ).build()
    }

    private val popular: LiveData<PagedList<Movie>> by lazy {
        LivePagedListBuilder(
            popularDataFactory,
            config
        ).build()
    }

    private var _filter = MutableLiveData<Int>().apply {
        postValue(UPCOMING_FILTER)
    }

    /**
     * Provides a [Movie] list to be presented at the View.
     */
    val movies: LiveData<PagedList<Movie>> = Transformations.switchMap(_filter) {
        when (it) {
            UPCOMING_FILTER -> upcoming
            POPULAR_FILTER -> popular
            else -> throw IllegalArgumentException()
        }
    }

    /**
     * Provides string resource to be shown as title.
     */
    val title: LiveData<Int> = Transformations.map(_filter) {
        when (it) {
            UPCOMING_FILTER -> R.string.upcoming_movies
            POPULAR_FILTER -> R.string.popular_movies
            else -> throw IllegalArgumentException()
        }
    }

    /**
     * Provides the selected filter value.
     */
    val filter: LiveData<Int> = _filter

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    /**
     * Set the selected filter as [UPCOMING_FILTER]
     */
    fun selectUpcoming() {
        _filter.postValue(UPCOMING_FILTER)
    }

    /**
     * Set the selected filter as [POPULAR_FILTER]
     */
    fun selectPopular() {
        _filter.postValue(POPULAR_FILTER)
    }

    companion object {
        private const val PAGE_SIZE = 15
        private const val UPCOMING_FILTER = 0
        private const val POPULAR_FILTER = 1
    }
}
