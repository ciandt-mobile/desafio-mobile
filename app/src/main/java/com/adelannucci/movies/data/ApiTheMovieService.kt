package com.adelannucci.movies.data

import com.adelannucci.movies.data.remote.ConnectivityInterceptor
import com.adelannucci.movies.data.remote.response.BaseResponse
import com.adelannucci.movies.data.remote.response.MovieDetailsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "132206372c1e05ef71d6942ade5881cd"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val IMAGE_URL = "http://image.tmdb.org/t/p/w300/"
interface ApiTheMovieService {

    @GET("movie/{filter}")
    fun getMovies(
        @Path("filter") filter: String,
        @Query(value = "page") location: Int,
        @Query(value = "language") languageCode: String = "en-US"
    ) : Call<BaseResponse>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Long,
        @Query(value = "language") languageCode: String = "en-US"
    ) : Call<MovieDetailsResponse>

    @GET("movie/{id}/cast")
    fun getCast(
        @Path("id") id: Int,
        @Query(value = "language") languageCode: String = "en-US"
    ) : Call<MovieDetailsResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiTheMovieService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val request = chain.request().newBuilder().url(url).build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiTheMovieService::class.java)
        }
    }
}