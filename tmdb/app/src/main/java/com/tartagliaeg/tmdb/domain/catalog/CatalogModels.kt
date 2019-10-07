package com.tartagliaeg.tmdb.domain.catalog

import com.google.gson.annotations.SerializedName
import com.tartagliaeg.tmdb.settings.ENV
import com.tartagliaeg.tmdb.tools.ParameterTools


class Cast(
    @SerializedName("cast_id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
) {
    constructor(
        id: Int = 0,
        name: String? = null,
        profilePath: String? = null,
        cast: Cast? = null
    ) : this(
        id = ParameterTools.firstNotEqualsTo(0, id, cast?.id ?: 0),
        name = ParameterTools.firstNotEqualsTo(null, name, cast?.name),
        profilePath = ParameterTools.firstNotEqualsTo(null, profilePath, cast?.profilePath)
    )
}

class Genre(
    @SerializedName("cast_id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String? = null
) {
    constructor(
        id: Int = 0,
        name: String? = null,
        genre: Genre? = null
    ) : this(
        id = ParameterTools.firstNotEqualsTo(0, id, genre?.id ?: 0),
        name = ParameterTools.firstNotEqualsTo(null, name, genre?.name)
    )
}

class Movie(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("runtime")
    val duration: Int = 0,
    @SerializedName("original_title")
    val title: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voterAverage: Float = 0f,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("popularity")
    val popularity: Float = 0f,
    @SerializedName("cast")
    val cast: List<Cast> = ArrayList(),
    @SerializedName("genres")
    val genres: List<Genre> = ArrayList()

) {

    // Builder Constructor
    constructor(
        id: Int = 0,
        title: String? = null,
        overview: String? = null,
        releaseDate: String? = null,
        posterPath: String? = null,
        voterAverage: Float = 0f,
        backdropPath: String? = null,
        popularity: Float = 0f,
        cast: List<Cast> = ArrayList(),
        genres: List<Genre> = ArrayList(),
        duration: Int = 0,
        movie: Movie? = null
    ) : this(
        id = ParameterTools.firstNotEqualsTo(0, id, movie?.id ?: 0),
        duration = ParameterTools.firstNotEqualsTo(0, duration, movie?.duration ?: 0),
        title = ParameterTools.firstNotEqualsTo(null, title, movie?.title),
        overview = ParameterTools.firstNotEqualsTo(null, overview, movie?.overview),
        releaseDate = ParameterTools.firstNotEqualsTo(null, releaseDate, movie?.releaseDate),
        posterPath = ParameterTools.firstNotEqualsTo(null, posterPath, movie?.posterPath),
        voterAverage = ParameterTools.firstNotEqualsTo(0f, voterAverage, movie?.voterAverage ?: 0f),
        backdropPath = ParameterTools.firstNotEqualsTo(null, backdropPath, movie?.backdropPath),
        popularity = ParameterTools.firstNotEqualsTo(0f, popularity, movie?.popularity ?: 0f),
        cast = ParameterTools.firstNotEqualsTo(ArrayList(), cast, movie?.cast ?: ArrayList()),
        genres = ParameterTools.firstNotEqualsTo(ArrayList(), genres, movie?.genres ?: ArrayList())
    )

    fun genresAsText(): String {
        return genres.map { it.name }.joinToString(separator = ", ")
    }

    fun year(): String? {
        return releaseDate?.split("-")?.get(0)
    }
}

class TMDBPage<Content>(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0,
    @SerializedName("results")
    val results: List<Content> = ArrayList()
) {
    constructor(
        page: Int = 0,
        totalPages: Int = 0,
        totalResults: Int = 0,
        results: List<Content> = ArrayList(),
        tmdb: TMDBPage<Content>? = null
    ) : this(
        page = ParameterTools.firstNotEqualsTo(0, page, tmdb?.page ?: 0),
        totalPages = ParameterTools.firstNotEqualsTo(0, totalPages, tmdb?.totalPages ?: 0),
        totalResults = ParameterTools.firstNotEqualsTo(0, totalResults, tmdb?.totalResults ?: 0),
        results = ParameterTools.firstNotEqualsTo(
            ArrayList(),
            results,
            tmdb?.results ?: ArrayList()
        )
    )
}

class MovieImages(
    val moviePosterSize: Int = 0,
    val movieBackdropSize: Int = 0,
    val castPosterSize: Int = 0
) {
    private companion object {
        val MOVIE_POSTER_SIZES = ENV.TMDB_API_STATIC_POSTER_DIMENSIONS
        val MOVIE_BACKDROP_SIZES = ENV.TMDB_API_STATIC_BACKDROP_DIMENSIONS
        val CAST_POSTER_SIZES = ENV.TMDB_API_STATIC_CAST_POSTER_DIMENSIONS
    }

    fun closestMoviePosterSizeW(): String {
        return if (moviePosterSize == 0) "original"
        else "w" + closestSize(moviePosterSize, MOVIE_POSTER_SIZES.toTypedArray())
    }

    fun closestMovieBackdropSizeW(): String {
        return if (movieBackdropSize == 0) "original"
        else "w" + closestSize(movieBackdropSize, MOVIE_BACKDROP_SIZES.toTypedArray())
    }

    fun closestCastPosterSizeW(): String {
        return if (castPosterSize == 0) "original"
        else "w" + closestSize(castPosterSize, CAST_POSTER_SIZES.toTypedArray())
    }


    private fun closestSize(size: Int, arr: Array<Int>): Int {
        var closest = 0

        for (dim in arr)
            if (kotlin.math.abs(size - dim) < kotlin.math.abs(size - closest))
                closest = dim

        return closest
    }

}

