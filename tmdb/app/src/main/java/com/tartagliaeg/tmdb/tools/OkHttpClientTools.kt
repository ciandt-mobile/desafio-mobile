package com.tartagliaeg.tmdb.tools

import okhttp3.Interceptor
import okhttp3.OkHttpClient

object OkHttpClientTools {
    fun createInterceptorWithDefaultQueryParameters(params: Map<String, String>): Interceptor {
        return Interceptor { chain ->
            val currentRequest = chain.request()
            val urlBuilder = currentRequest.url().newBuilder()

            for((k,v) in params )
                urlBuilder.addQueryParameter(k, v)

            val url = urlBuilder.build()

            chain.proceed(currentRequest.newBuilder().url(url).build())
        }
    }

    fun createClientWithDefaultQueryParameters(params: Map<String, String>): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(createInterceptorWithDefaultQueryParameters(params)).build()
    }
}