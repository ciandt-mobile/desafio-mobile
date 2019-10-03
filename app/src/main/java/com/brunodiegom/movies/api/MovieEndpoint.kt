package com.brunodiegom.movies.api

import com.brunodiegom.movies.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Endpoint request to the server.
 */
interface MovieEndpoint {
    @GET("/3/movie/upcoming")
    fun getUpcoming(): Single<MovieResponse>

    @GET("/3/movie/popular")
    fun getPopular(): Single<MovieResponse>
}
