package com.apolo.findmovies.data.remote

import com.apolo.findmovies.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
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
        //TODO: Put request on BuildConfig
        private val uri = "https://www.google.com.br"

        private fun createWebService(uri: String, gson: Gson ) : WebService {


            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(loggingInterceptor)

            val retrofit = Retrofit.Builder().baseUrl(uri).client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()

            return retrofit.create(WebService::class.java)

        }

        private fun getGson(): Gson {
            return GsonBuilder().setDateFormat(DATE_FORMAT).create()
        }

    }



}