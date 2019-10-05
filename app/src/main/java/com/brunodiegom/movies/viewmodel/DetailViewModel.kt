package com.brunodiegom.movies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunodiegom.movies.api.MovieRepository
import com.brunodiegom.movies.model.Detail
import io.reactivex.disposables.CompositeDisposable

/**
 * [ViewModel] used to provide the [Detail] data to the view.
 */
class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val _detail = MutableLiveData<Detail>()

    fun start(id: Int) {
        Log.d("TESTE", "Start: $id")
        if (id == DEFAULT_ID) return

        disposable.add(
            movieRepository.getDetail(id).subscribe({
                Log.d("TESTE", "Title: ${it.title}")
                _detail.postValue(it)
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    companion object {
        const val DEFAULT_ID = -1
    }
}
