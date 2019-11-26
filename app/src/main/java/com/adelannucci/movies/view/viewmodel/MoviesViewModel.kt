package com.adelannucci.movies.view.viewmodel

import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.adelannucci.movies.R
import com.adelannucci.movies.data.remote.TheMovieDataSource
import com.adelannucci.movies.data.remote.response.MovieResponse

class MoviesViewModel(val repository: TheMovieDataSource, val context: Context) {

    var filter: FilterMoviesEnum = FilterMoviesEnum.UPCOMING
    val movies = ObservableArrayList<MovieResponse>()
    val loadingVisibility = ObservableBoolean(false)
    val message = ObservableField<String>()

    fun load(newFilter: FilterMoviesEnum) {
        filter = newFilter
        loadingVisibility.set(true)
        message.set("")
        repository.getMovies(filter.value,1, "en-US",
            { items ->
                movies.clear()
                movies.addAll(items)
                if (items.isEmpty()) {
                    message.set(context.getString(R.string.movies_empty))
                }
                loadingVisibility.set(false)
            }, {
                message.set(context.getString(R.string.movies_failed))
                loadingVisibility.set(false)
            })
    }
}