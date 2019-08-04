package br.com.architerure.movies.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesService private constructor (val context: Context) {


    private val retrofit: Retrofit
    private val api: ApiService


    companion object {
        fun instance(context: Context) : MoviesService {
            return MoviesService(context)
        }
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    fun apiInterface() : ApiService {
        return api
    }

    val okHttpClient: OkHttpClient
        get() {
            val httpClient = OkHttpClient.Builder()
//            httpClient.addInterceptor { chain ->
//                val original = chain.request()
//                val requestBuilder = original.newBuilder()
//                    .addHeader("Content-Type", "application/json")
//
//                val request = requestBuilder.build()
//                chain.proceed(request)
//            }
            val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            httpClient.addInterceptor(logging).build()

            return httpClient.build()
        }

}