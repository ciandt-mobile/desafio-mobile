/*
 * SearchMoviesRepository.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 17:46
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.model.repository

import br.com.codigozeroum.desafiomobile.factory.ServiceFactory
import br.com.codigozeroum.desafiomobile.features.home.model.MoviesResponse
import br.com.codigozeroum.desafiomobile.features.home.model.service.MovieService
import br.com.codigozeroum.desafiomobile.projectStructure.BaseRepository
import io.reactivex.Single

class SearchMoviesRepository : BaseRepository() {

    private val service: MovieService = ServiceFactory().create(search = true)

    fun searchMovie(query: String, page: Int): Single<MoviesResponse>{
        return doCall(service.searchMovie(query = query, page = page))
    }
}