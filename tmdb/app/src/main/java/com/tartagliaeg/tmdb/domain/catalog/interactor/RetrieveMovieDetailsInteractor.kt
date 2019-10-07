package com.tartagliaeg.tmdb.domain.catalog.interactor

import com.tartagliaeg.tmdb.domain.catalog.CatalogRepositoryContract
import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import com.tartagliaeg.tmdb.tools.KeyTools
import com.tartagliaeg.tmdb.tools.SimpleMemoryCache
import io.reactivex.Single
import io.reactivex.SingleTransformer

typealias RetrieveMovieDetailsInteractor = (id: Int) -> SingleTransformer<CatalogRepositoryContract, Movie>

val retrieveMovieDetailsInteractor : RetrieveMovieDetailsInteractor = { page: Int ->
    SingleTransformer { stream ->
        stream.flatMap { repo ->
            repo.getMovieDetails(page)
        }
    }
}


