package com.adelannucci.movies.view.viewmodel.movies

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.adelannucci.movies.data.remote.TheMovieDataSource
import com.adelannucci.movies.data.remote.response.MovieResponse
import com.adelannucci.movies.view.viewmodel.FilterMoviesEnum

class MoviesViewModel(val repository: TheMovieDataSource) {

    var filter: FilterMoviesEnum =
        FilterMoviesEnum.UPCOMING
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
                loadingVisibility.set(false)
            }, {
                loadingVisibility.set(false)
            })
    }
}