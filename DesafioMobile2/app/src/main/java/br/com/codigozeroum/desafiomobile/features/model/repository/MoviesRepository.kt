/*
 * MoviesRepository.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:14
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.model.repository

import br.com.codigozeroum.desafiomobile.factory.ServiceFactory
import br.com.codigozeroum.desafiomobile.features.model.service.MovieService
import br.com.codigozeroum.desafiomobile.features.model.PopularResponse
import br.com.codigozeroum.desafiomobile.projectStructure.BaseRepository
import io.reactivex.Single

class MoviesRepository : BaseRepository() {

    private val service: MovieService = ServiceFactory().create()

    fun getPopularMovies(): Single<PopularResponse>{
        return doCall(service.getPopularMovies())
    }

    fun getTopRatedMovies(): Single<PopularResponse>{
        return doCall(service.getTopRatedMovies())
    }

    fun getUpcomingMovies(): Single<PopularResponse>{
        return doCall(service.getUpcomingMovies())
    }
}