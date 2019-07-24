package com.msaviczki.themovieapp.presentation.detail.viewmodel

import com.msaviczki.themovieapp.data.MovieDetailMap
import com.msaviczki.themovieapp.data.MovieResponse
import com.msaviczki.themovieapp.helper.extension.safeRequestCall
import com.msaviczki.themovieapp.network.core.ApiConstants
import com.msaviczki.themovieapp.network.core.Result
import com.msaviczki.themovieapp.network.networkinterface.MovieDetailApi
import com.msaviczki.themovieapp.network.core.Result.Success
import com.msaviczki.themovieapp.network.core.Result.Error
import java.lang.StringBuilder

interface MovieDetailViewModelRepository {
    suspend fun getMovieById(id: Int): Result<MovieDetailMap>
}

class MovieDetailViewModelRepositoryImpl(private val api: MovieDetailApi) : MovieDetailViewModelRepository {

    override suspend fun getMovieById(id: Int) =
        safeRequestCall { requestMovieById(id) }

    private suspend fun requestMovieById(id: Int): Result<MovieDetailMap> {
        val response = api.getMovieById(
            id,
            ApiConstants.API_KEY
        ).await()

        response?.let {
            return Success(it.map())
        }
        return Error("Erro ao fazer requisição")
    }

    private fun MovieResponse.map(): MovieDetailMap {
        val builder = StringBuilder()
        genres.forEach {
            builder.append(it.name + " ")
        }

        return MovieDetailMap(
            title,
            realeseDate,
            image,
            backdropPath,
            time,
            overview,
            rating = rating.toString(),
            genre = builder.toString(),
            originalTitle = originalTitle,
            originalLanguage = originalLanguage
        )
    }


}