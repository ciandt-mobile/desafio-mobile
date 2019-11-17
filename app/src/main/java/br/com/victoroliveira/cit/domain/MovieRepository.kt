package br.com.victoroliveira.cit.domain

import br.com.victoroliveira.cit.data.model.CastResponse
import br.com.victoroliveira.cit.data.model.DetailResponse
import br.com.victoroliveira.cit.data.model.PopularResponse
import br.com.victoroliveira.cit.data.model.UpcomingResponse

interface MovieRepository {

    suspend fun getUpcoming(page: Int,language:String): UpcomingResponse
    suspend fun getPopular(page: Int,language:String): PopularResponse
    suspend fun getCast(id: Int): CastResponse
    suspend fun getDetail(id: Int): DetailResponse
}