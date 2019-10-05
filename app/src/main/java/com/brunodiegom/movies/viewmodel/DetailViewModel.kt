package com.brunodiegom.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.model.Cast
import com.brunodiegom.movies.model.Detail
import com.brunodiegom.movies.model.Genre
import io.reactivex.disposables.CompositeDisposable

/**
 * [ViewModel] used to provide the [Detail] data to the view.
 */
class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val detail = MutableLiveData<Detail>()

    private val _cast = MutableLiveData<List<Cast>>()

    val title: LiveData<String> = Transformations.map(detail) { it.title }

    val release: LiveData<String> = Transformations.map(detail) { it.release.split("-")[0] }

    val summary: LiveData<String> = Transformations.map(detail) { "${it.duration}m | ${buildGenres(it.genres)}" }

    val overview: LiveData<String> = Transformations.map(detail) { it.overview }

    val backdropUrl: LiveData<String> = Transformations.map(detail) { "${Detail.BACKDROP_BASE_URL}${it.backdropUrl}" }

    val cast: LiveData<List<Cast>> = _cast

    /**
     * Initialize [ViewModel] loading, requesting the [Detail] and [Cast] data.
     */
    fun start(id: Int) {
        if (id == DEFAULT_ID) return
        disposable.add(
            movieRepository.getDetail(id).subscribe({
                detail.postValue(it)
            }, {
                it.printStackTrace()
            })
        )
        disposable.add(
            movieRepository.getCast(id).subscribe({
                _cast.postValue(it.cast)
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    private fun buildGenres(genres: List<Genre>) = genres.joinToString { it.name }

    companion object {
        const val DEFAULT_ID = -1
    }
}
