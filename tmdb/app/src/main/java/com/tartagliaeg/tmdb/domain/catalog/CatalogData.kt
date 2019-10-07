package com.tartagliaeg.tmdb.domain.catalog

import com.tartagliaeg.tmdb.tools.RxTools
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CatalogAPI {
    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") page: Int): Single<TMDBPage<Movie>>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Single<Movie>

    @GET("movie/{id}/credits")
    fun getMovieCast(@Path("id") id: Int): Single<Movie>

}

interface CatalogRepositoryContract {
    fun getUpcomingMovies(page: Int): Single<TMDBPage<Movie>>
    fun getMovieDetails(id: Int): Single<Movie>
}

class CatalogRepository(private val api: CatalogAPI) : CatalogRepositoryContract {
    override fun getUpcomingMovies(page: Int): Single<TMDBPage<Movie>> {
        return RxTools.ioToMainPipe(api.getUpcomingMovies(page))
    }

    override fun getMovieDetails(id: Int): Single<Movie> {
        return RxTools.ioToMainPipe(api.getMovieDetails(id))
            .flatMap { movie ->
                RxTools.ioToMainPipe(api.getMovieCast((id))).map { Movie(movie = movie, cast = it.cast) }
            }
    }
}

