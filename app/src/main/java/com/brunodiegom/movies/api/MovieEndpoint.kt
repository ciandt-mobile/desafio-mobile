package com.brunodiegom.movies.api

import com.brunodiegom.movies.model.CastResponse
import com.brunodiegom.movies.model.Detail
import com.brunodiegom.movies.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Endpoint request to the server.
 */
interface MovieEndpoint {
    @GET("/3/movie/upcoming")
    fun getUpcoming(@Query("page") page: Int?): Single<MovieResponse>

    @GET("/3/movie/popular")
    fun getPopular(@Query("page") page: Int?): Single<MovieResponse>

    @GET("/3/movie/{id}")
    fun getDetail(@Path("id") id: Int?): Single<Detail>

    @GET("/3/movie/{id}/credits")
    fun getCast(@Path("id") id: Int?): Single<CastResponse>
}
