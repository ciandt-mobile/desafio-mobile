package com.brunodiegom.movies.model.upcoming

import androidx.paging.PageKeyedDataSource
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.model.Movie
import io.reactivex.disposables.CompositeDisposable

/**
 * Upcoming paged data source.
 */
class UpcomingDataSource(
    private val disposable: CompositeDisposable,
    private val movieRepository: MovieRepository
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        disposable.add(
            movieRepository.getUpcoming().subscribe({
                callback.onResult(it.results, it.page, it.totalResults, null, params.requestedLoadSize + 1)
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        disposable.add(
            movieRepository.getUpcoming().subscribe({
                val next = if (params.key < PAGE_LIMIT) params.key + 1 else null
                callback.onResult(it.results, next)
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        // Nothing to be done.
    }

    companion object {
        private const val PAGE_LIMIT = 20
    }
}
