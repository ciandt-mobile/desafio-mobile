package com.apolo.findmovies.data.remote

import com.apolo.findmovies.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebServiceClient {

    var service: WebService
        private set

    constructor() {
        service = WebServiceClient.createWebService(uri, getGson())
    }

    companion object {

        private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val API_KEY = BuildConfig.API_KEY
        private const val PARAMETER_API_KEY = "api_key"
        private const val uri = BuildConfig.API_ENDPOINT

        private fun createWebService(uri: String, gson: Gson ) : WebService {

            val requestInterceptor =  object :Interceptor  {
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    val originalHttpUrl = request.url

                    val url = originalHttpUrl.newBuilder().addQueryParameter(PARAMETER_API_KEY, API_KEY)
                        .build()

                    val builder = request.newBuilder()
                    builder.url(url)

                    request = builder.build()
                    return chain.proceed(request)
                }
            }

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(loggingInterceptor)
            httpClientBuilder.addInterceptor(requestInterceptor)

            httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
            httpClientBuilder.readTimeout(30, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder().baseUrl(uri).client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()

            return retrofit.create(WebService::class.java)

        }

        private fun getGson(): Gson {
            return GsonBuilder().setDateFormat(DATE_FORMAT).create()
        }

    }



}