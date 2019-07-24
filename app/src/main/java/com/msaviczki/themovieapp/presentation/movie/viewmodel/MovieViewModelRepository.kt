package com.msaviczki.themovieapp.presentation.movie.viewmodel

import com.msaviczki.themovieapp.data.MovieMap
import com.msaviczki.themovieapp.data.MovieResponse
import com.msaviczki.themovieapp.helper.extension.safeRequestCall
import com.msaviczki.themovieapp.network.core.ApiConstants
import com.msaviczki.themovieapp.network.core.Result.Success
import com.msaviczki.themovieapp.network.core.Result.Error
import com.msaviczki.themovieapp.network.core.Result
import com.msaviczki.themovieapp.network.networkinterface.MovieApi


interface MovieViewModelRepository {
    suspend fun requestPopularMovies(): Result<MutableList<MovieMap>>
    suspend fun requestUpcomingMovies(): Result<MutableList<MovieMap>>
}

class MovieViewModelRepositoryImpl(private val api: MovieApi) : MovieViewModelRepository {

    override suspend fun requestPopularMovies() =
        safeRequestCall { getPopularMovies() }

    override suspend fun requestUpcomingMovies() =
        safeRequestCall { getUpComingMovies() }

    private suspend fun getPopularMovies(): Result<MutableList<MovieMap>> {
        val response = api.getPopularMovies(
            ApiConstants.API_KEY,
            ApiConstants.LANGUAGE,
            ApiConstants.PAGE
        ).await()

        response.results?.let {
            return Success(response.results.map())
        }
        return Error("Erro ao fazer requisição")
    }

    private suspend fun getUpComingMovies(): Result<MutableList<MovieMap>> {
        val response = api.getUpComingMovies(
            ApiConstants.API_KEY,
            ApiConstants.LANGUAGE,
            ApiConstants.PAGE
        ).await()

        response.results?.let {
            return Success(response.results.map())
        }
        return Error("Erro ao fazer requisição")
    }

    private fun MutableList<MovieResponse>.map(): MutableList<MovieMap> {
        val list: MutableList<MovieMap> = mutableListOf()
        this.forEach {
            val movie = MovieMap(
                title = it.title,
                realeseDate = it.realeseDate,
                image = it.image,
                id = it.id
            )
            list.add(movie)
        }
        return list
    }
}