/*
 * MoviesRepository.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:14
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.model.repository

import br.com.codigozeroum.desafiomobile.factory.ServiceFactory
import br.com.codigozeroum.desafiomobile.features.home.model.service.MovieService
import br.com.codigozeroum.desafiomobile.features.home.model.MoviesResponse
import br.com.codigozeroum.desafiomobile.projectStructure.BaseRepository
import io.reactivex.Single

class MoviesRepository : BaseRepository() {

    private val service: MovieService = ServiceFactory().create()

    fun getUpcomingMovies(page: Int): Single<MoviesResponse>{
        return doCall(service.getUpcomingMovies(page = page))
    }

    fun getTopRatedMovies(page: Int): Single<MoviesResponse>{
        return doCall(service.getTopRatedMovies(page = page))
    }

    fun getPopularMovies(page: Int): Single<MoviesResponse>{
        return doCall(service.getPopularMovies(page = page))
    }



}