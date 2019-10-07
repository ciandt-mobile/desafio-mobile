package com.tartagliaeg.tmdb.settings

import com.tartagliaeg.tmdb.BuildConfig

object ENV {
    val TMDB_API_REST_URL = BuildConfig.TMDB_API_REST_URL
    val TMDB_API_REST_AUTH_KEY = BuildConfig.TMDB_API_REST_AUTH_KEY
    val TMDB_API_REST_AUTH_VAL = BuildConfig.TMDB_API_REST_AUTH_VAL
    val TMDB_API_STATIC_URL = BuildConfig.TMDB_API_STATIC_URL
    val TMDB_API_STATIC_URL_PARAMS_SIZE = BuildConfig.TMDB_API_STATIC_URL_PARAMS_SIZE

    val TMDB_API_STATIC_BACKDROP_DIMENSIONS = BuildConfig.TMDB_API_STATIC_BACKDROP_DIMENSIONS
        .filter { it.toIntOrNull() != null }
        .map { it.toInt() }

    val TMDB_API_STATIC_POSTER_DIMENSIONS = BuildConfig.TMDB_API_STATIC_POSTER_DIMENSIONS
        .filter { it.toIntOrNull() != null }
        .map { it.toInt() }

    val TMDB_API_STATIC_CAST_POSTER_DIMENSIONS = BuildConfig.TMDB_API_STATIC_POSTER_DIMENSIONS
        .filter { it.toIntOrNull() != null }
        .map { it.toInt() }

}

