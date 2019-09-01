/*
 * MovieDetailRepository.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 03:47
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.movie_details

import br.com.codigozeroum.desafiomobile.factory.ServiceFactory
import br.com.codigozeroum.desafiomobile.features.movie_details.model.MovieDetail
import br.com.codigozeroum.desafiomobile.projectStructure.BaseRepository
import io.reactivex.Single

class MovieDetailRepository : BaseRepository() {

    private val service: MovieDetailService = ServiceFactory().create()

    fun getMovieDetail(movieId: Int): Single<MovieDetail> {
        return doCall(service.getMovieDetail(movieId))
    }
}