package com.brunodiegom.movies.api

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Endpoint to server used to build the [OkHttpClient] to request data.
 */
class Endpoint {

    /**
     * [Retrofit] instance used to request data.
     */
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val httpClient: OkHttpClient by lazy { getBuilder().build() }

    private fun getBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder().let {
            it.addInterceptor { chain ->
                chain.proceed(chain.request().run {
                    newBuilder().url(getHttpUrl(this)).build()
                })
            }
            it.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }

    private fun getHttpUrl(request: Request) =
        request.url().newBuilder().setEncodedQueryParameter(
            API_KEY_PARAM_NAME,
            API_KEY
        ).build()

    companion object {
        private const val API_KEY_PARAM_NAME = "api_key"
        private const val API_KEY = "b0b8d53c71ff43302de112562a7e4c37"
        private const val BASE_URL = "https://api.themoviedb.org/"
    }
}
