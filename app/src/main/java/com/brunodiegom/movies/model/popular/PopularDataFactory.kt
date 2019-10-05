package com.brunodiegom.movies.model.popular

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.model.Movie
import io.reactivex.disposables.CompositeDisposable

/**
 * [DataSource.Factory] used by to load paged data.
 */
class PopularDataFactory(
    private val disposable: CompositeDisposable,
    private val movieRepository: MovieRepository
) : DataSource.Factory<Int, Movie>() {

    private val popular = MutableLiveData<PopularDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val popularDataSource = PopularDataSource(disposable, movieRepository)
        popular.postValue(popularDataSource)
        return popularDataSource
    }
}
