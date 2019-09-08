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

class WebServiceClient {

    var service: WebService
        private set

    constructor() {
        service = WebServiceClient.createWebService(uri, getGson())
    }

    companion object {

        private val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private val API_KEY = "b3a35d2b18aa95389f72b881c0ca08f8"
        private val PARAMETER_API_KEY = "api_key"
        //TODO: Put request on BuildConfig
        private val uri = "https://api.themoviedb.org/3/"

        private val imagesUrl = "https://image.tmdb.org/t/p/w500/"

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

            val retrofit = Retrofit.Builder().baseUrl(uri).client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()

            return retrofit.create(WebService::class.java)

        }

        private fun getGson(): Gson {
            return GsonBuilder().setDateFormat(DATE_FORMAT).create()
        }

    }



}