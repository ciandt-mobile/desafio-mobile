package br.com.victoroliveira.cit.data.remote

import br.com.victoroliveira.cit.data.model.CastResponse
import br.com.victoroliveira.cit.data.model.DetailResponse
import br.com.victoroliveira.cit.data.model.PopularResponse
import br.com.victoroliveira.cit.data.model.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int,
        @Query("language") language: String

    ): UpcomingResponse

    @GET("/3/movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int,
        @Query("language") language: String

    ): PopularResponse

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") movie_id: Int
    ): CastResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movie_id: Int
    ): DetailResponse
}