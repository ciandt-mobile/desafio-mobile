package br.com.suelen.mobilechallenge.utilities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitService {

    companion object {

        private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun <S> createService(serviceClass : Class<S>) : S {
            return retrofit.create(serviceClass)
        }
    }
}