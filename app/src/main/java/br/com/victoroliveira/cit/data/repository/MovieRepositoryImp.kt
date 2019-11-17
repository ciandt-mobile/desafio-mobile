package br.com.victoroliveira.cit.data.repository

import br.com.victoroliveira.cit.data.model.CastResponse
import br.com.victoroliveira.cit.data.model.DetailResponse
import br.com.victoroliveira.cit.data.model.PopularResponse
import br.com.victoroliveira.cit.data.model.UpcomingResponse
import br.com.victoroliveira.cit.data.remote.ApiService
import br.com.victoroliveira.cit.domain.MovieRepository

class MovieRepositoryImp(
    private val apiService: ApiService
) : MovieRepository {
    override suspend fun getUpcoming(page: Int, language: String): UpcomingResponse {
        return apiService.getUpcoming(page, language)
    }

    override suspend fun getPopular(page: Int, language: String): PopularResponse {
        return apiService.getPopular(page, language)
    }

    override suspend fun getCast(id: Int): CastResponse {
        return apiService.getCast(id)
    }

    override suspend fun getDetail(id: Int): DetailResponse {
        return apiService.getDetail(id)
    }
}