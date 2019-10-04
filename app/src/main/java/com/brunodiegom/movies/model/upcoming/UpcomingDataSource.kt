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
                callback.onResult(it.results, null, 2)
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        disposable.add(
            movieRepository.getUpcoming(params.key).subscribe({
                callback.onResult(it.results, params.key + 1)
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        // Nothing to be done.
    }
}
