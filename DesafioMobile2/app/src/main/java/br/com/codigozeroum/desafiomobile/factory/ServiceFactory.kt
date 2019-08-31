/*
 * ServiceFactory.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 06:28
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.factory

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object API {
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
}

class ServiceFactory {

    inline fun <reified T> create(): T {
        val restAdapter = Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return restAdapter.create(T::class.java)
    }

}