package com.tartagliaeg.tmdb.domain.catalog.interactor

import com.tartagliaeg.tmdb.domain.catalog.CatalogRepositoryContract
import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import com.tartagliaeg.tmdb.tools.KeyTools
import com.tartagliaeg.tmdb.tools.SimpleMemoryCache
import io.reactivex.Single
import io.reactivex.SingleTransformer

typealias RetrieveUpcomingCatalogPageInteractor = (page: Int) -> SingleTransformer<CatalogRepositoryContract, TMDBPage<Movie>>

val retrieveUpcomingCatalogPage: RetrieveUpcomingCatalogPageInteractor = { page: Int ->
    SingleTransformer { stream ->
        stream.flatMap { repo ->
            repo.getUpcomingMovies(page)
        }
    }
}


typealias RetrieveCachedUpcomingCatalogPageInteractor = (page: Int, cache: SimpleMemoryCache) -> SingleTransformer<CatalogRepositoryContract, TMDBPage<Movie>>

val retrieveCachedUpcomingCatalogPage: RetrieveCachedUpcomingCatalogPageInteractor = { page: Int, cache: SimpleMemoryCache ->
    val cacheKey = KeyTools.compose("retrieveUpcomingCatalogPage", page.toString())

    SingleTransformer { stream ->
        stream.flatMap { repo ->
            if(cache.has(cacheKey)) Single.just(cache.get<TMDBPage<Movie>>(cacheKey))
            else repo.getUpcomingMovies(page)
        }
        .doOnSuccess { cache.set(cacheKey, it) }
    }
}

