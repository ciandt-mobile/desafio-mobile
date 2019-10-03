package com.brunodiegom.movies.api

import com.brunodiegom.movies.model.MovieResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Data repository to abstract from server connection.
 */
class MovieRepository(private val endpoint: Endpoint) {

    fun getUpcoming(): Single<MovieResponse> =
        createEndpoint().getUpcoming().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun getPopular(): Single<MovieResponse> =
        createEndpoint().getPopular().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    private fun createEndpoint() = endpoint.retrofit.create(MovieEndpoint::class.java)
}
