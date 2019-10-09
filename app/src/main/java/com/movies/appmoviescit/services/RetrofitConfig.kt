package com.movies.appmoviescit.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private var client: OkHttpClient
    private var retrofit: Retrofit

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(getBaseURL())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun movieService() = retrofit.create(IMovieService::class.java)

    protected open fun getBaseURL() = MoviesConstants.API_URL_BASE

}