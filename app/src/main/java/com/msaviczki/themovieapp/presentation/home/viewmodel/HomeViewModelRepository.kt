package com.msaviczki.themovieapp.presentation.home.viewmodel

import com.msaviczki.themovieapp.data.MovieListResponse
import com.msaviczki.themovieapp.helper.extension.safeRequestCall
import com.msaviczki.themovieapp.network.core.Result
import com.msaviczki.themovieapp.network.networkinterface.MovieApi


interface HomeViewModelRepositoryImpl {
    suspend fun requestMovies(): Result<MovieListResponse>
}

class HomeViewModelRepository(private val api: MovieApi) : HomeViewModelRepositoryImpl {

    override suspend fun requestMovies() =
        safeRequestCall { getMovies() }

    private suspend fun getMovies(): Result<MovieListResponse> {
        val response = api.getPopularMovies().await()
        response.results?.let {
            return Result.Success(response)
        }
        return Result.Error("Erro na requisição")
    }
}