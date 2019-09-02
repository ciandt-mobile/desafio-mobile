/*
 * MovieService.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:17
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.model.service

import br.com.codigozeroum.desafiomobile.features.home.model.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("upcoming")
    fun getUpcomingMovies(@Query("api_key") api_key: String = "addca3fa3c1a2696ce99790477d09a7b",
                          @Query("page") page: Int): Single<MoviesResponse>

    @GET("top_rated")
    fun getTopRatedMovies(@Query("api_key") api_key: String = "addca3fa3c1a2696ce99790477d09a7b",
                          @Query("page") page: Int): Single<MoviesResponse>

    @GET("popular")
    fun getPopularMovies(@Query("api_key") api_key: String = "addca3fa3c1a2696ce99790477d09a7b",
                         @Query("page") page: Int): Single<MoviesResponse>

    @GET("movie")
    fun searchMovie(@Query("api_key") api_key: String = "addca3fa3c1a2696ce99790477d09a7b",
                    @Query("query") query: String,
                    @Query("page") page: Int): Single<MoviesResponse>

}