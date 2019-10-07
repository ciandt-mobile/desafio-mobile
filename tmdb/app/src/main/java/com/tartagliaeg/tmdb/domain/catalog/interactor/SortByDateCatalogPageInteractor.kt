package com.tartagliaeg.tmdb.domain.catalog.interactor

import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import io.reactivex.SingleTransformer


typealias SortByDateCatalogPageInteractor = () -> SingleTransformer<TMDBPage<Movie>, TMDBPage<Movie>>

val sortByDateCatalogPage: SortByDateCatalogPageInteractor = {
    SingleTransformer { stream ->
        stream.map { input -> TMDBPage(
            tmdb = input,
            results = input.results.sortedBy { movie -> movie.releaseDate }
        )}
    }
}
