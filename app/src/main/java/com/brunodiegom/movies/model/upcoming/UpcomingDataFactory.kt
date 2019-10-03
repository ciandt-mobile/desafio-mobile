package com.brunodiegom.movies.model.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.model.Movie
import io.reactivex.disposables.CompositeDisposable

/**
 * [DataSource.Factory] used by to load paged data.
 */
class UpcomingDataFactory(
    private val disposable: CompositeDisposable,
    private val movieRepository: MovieRepository
) : DataSource.Factory<Int, Movie>() {

    /**
     * Provides the [MutableLiveData] representing the [UpcomingDataSource].
     */
    val upcoming = MutableLiveData<UpcomingDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val upcomingDataSource = UpcomingDataSource(disposable, movieRepository)
        upcoming.postValue(upcomingDataSource)
        return upcomingDataSource
    }
}
