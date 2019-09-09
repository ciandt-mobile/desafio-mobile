package com.conrradocamacho.desafio.network.service

import com.conrradocamacho.desafio.BuildConfig
import com.conrradocamacho.desafio.network.bean.Credit
import com.conrradocamacho.desafio.network.bean.Detail
import com.conrradocamacho.desafio.network.bean.Discover
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Conrrado Camacho on 03/09/2019.
 * con.webmaster@gmail.com
 */
interface AppAPI {

    @Headers(
        "Content-type: application/json",
        "Authorization: " + BuildConfig.TOKEN
    )

    @GET("discover/movie?sort_by=popularity.desc&api_key=${BuildConfig.API_KEY}")
    fun getMovies(): Call<Discover>

    @GET("movie/{id}?api_key=${BuildConfig.API_KEY}")
    fun getDetails(@Path(value = "id") movieId: Int): Call<Detail>

    @GET("movie/{id}/credits?api_key=${BuildConfig.API_KEY}")
    fun getCredits(@Path(value = "id") movieId: Int): Call<Credit>
}