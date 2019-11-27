package com.adelannucci.movies.view.viewmodel.details

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.widget.ImageView
import com.adelannucci.movies.data.remote.TheMovieDataSource
import com.adelannucci.movies.data.remote.response.MovieDetailsResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MovieDetailsViewModel(val repository: TheMovieDataSource, val view: ImageView) {

    val genres = ObservableField<String>()
    val casts = ObservableField<String>()
    val runtime = ObservableField<String>()
    val movie = ObservableField<MovieDetailsResponse>()
    val loadingVisibility = ObservableBoolean(false)

    fun load(id: Long) {
        loadingVisibility.set(true)
        repository.getMovie(id, "en-US",
            { item ->
                val genresValue = item.genres?.map {
                    it.name
                }.joinToString(
                    separator = ", ",
                    truncated = ""
                )
                val creditsValue = item.casts.cast?.map {
                    it.name
                }.joinToString(
                    separator = ", ",
                    truncated = ""
                )
                casts.set(creditsValue)
                genres.set(genresValue)
                runtime.set("${item.runtime} min")
                loadImage("https://image.tmdb.org/t/p/w780" + item.backdropPath)
                movie.set(item)
                loadingVisibility.set(false)
            }, {
                loadingVisibility.set(false)
            })
    }

    private fun loadImage(url: String) {
        Glide.with(view.getContext())
            .load(url).apply(RequestOptions().centerCrop())
            .into(view)
    }
}