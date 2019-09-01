/*
 * MovieDetailService.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 18:54
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.movie_details

import br.com.codigozeroum.desafiomobile.features.movie_details.model.MovieDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("{movieId}")
    fun getMovieDetail(@Path("movieId") movieId: Int, @Query("api_key") api_key: String = "addca3fa3c1a2696ce99790477d09a7b"): Single<MovieDetail>
}