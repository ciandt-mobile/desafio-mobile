package com.conrradocamacho.desafio.network.service

import com.conrradocamacho.desafio.BuildConfig
import com.conrradocamacho.desafio.network.bean.Discover
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by Conrrado Camacho on 03/09/2019.
 * con.webmaster@gmail.com
 */
interface AppAPI {

    @Headers(
        "Content-type: application/json",
        "Authorization: " + BuildConfig.TOKEN
    )

    @GET("discover/movie?sort_by=popularity.desc&api_key="+BuildConfig.API_KEY)
    fun getMovies(): Call<Discover>
}