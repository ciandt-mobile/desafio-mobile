package com.tartagliaeg.tmdb.domain.catalog.interactor

import com.tartagliaeg.tmdb.domain.catalog.Cast
import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.MovieImages
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import com.tartagliaeg.tmdb.settings.ENV
import com.tartagliaeg.tmdb.tools.UriTools
import io.reactivex.Single
import io.reactivex.SingleTransformer


internal fun prepareUrlForOneOfSizes (size: String): String {
    return ENV.TMDB_API_STATIC_URL.replace(
        ENV.TMDB_API_STATIC_URL_PARAMS_SIZE,
        size
    )
}

internal fun resolveMovie(movieBackdrop: String, moviePoster: String, castPoster: String, movie: Movie): Movie {
    return Movie(
        movie = movie,
        backdropPath = UriTools.join(movieBackdrop, movie.backdropPath),
        posterPath = UriTools.join(moviePoster, movie.posterPath),
        cast = movie.cast.map { cast ->
            Cast(
                cast = cast,
                profilePath = UriTools.join(castPoster, cast.profilePath)
            )
        }
    )

}

typealias ResolveImagesForCatalogPageInteractor = (images: MovieImages) -> SingleTransformer<TMDBPage<Movie>, TMDBPage<Movie>>


val resolveImagesForCatalogPage: ResolveImagesForCatalogPageInteractor = { images: MovieImages ->
    SingleTransformer { it.flatMap { input: TMDBPage<Movie> ->

        val movieBackdrop = prepareUrlForOneOfSizes(images.closestMovieBackdropSizeW())
        val moviePoster = prepareUrlForOneOfSizes(images.closestMoviePosterSizeW())
        val castPoster = prepareUrlForOneOfSizes(images.closestCastPosterSizeW())

        resolveImagesForMovie

        Single.just(TMDBPage(
            tmdb = input,
            results = input.results.map { movie ->
                resolveMovie(
                    movieBackdrop,
                    moviePoster,
                    castPoster,
                    movie
                )
            }
        ))
    }}
}


typealias ResolveImagesForMovieInteractor = (images: MovieImages) -> SingleTransformer<Movie, Movie>


val resolveImagesForMovie: ResolveImagesForMovieInteractor = { images: MovieImages ->
    SingleTransformer { it.flatMap { input: Movie ->

        val movieBackdrop = prepareUrlForOneOfSizes(images.closestMovieBackdropSizeW())
        val moviePoster = prepareUrlForOneOfSizes(images.closestMoviePosterSizeW())
        val castPoster = prepareUrlForOneOfSizes(images.closestCastPosterSizeW())

        Single.just(
            resolveMovie(
                movieBackdrop,
                moviePoster,
                castPoster,
                input
            )
        )
    }}
}



