package com.nurik.desafiomobile.data

import com.nurik.desafiomobile.pojo.Movie
import com.nurik.desafiomobile.pojo.ResponseGenre
import com.nurik.desafiomobile.pojo.ResponseMovie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
   /* @GET("movie/popular")
    fun callPopularMovies(@Query("api_key") api_key: String): Call<ResponseMovie>*/

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String): Response<ResponseMovie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key: String): Response<ResponseMovie>

    @GET("genre/movie/list")
    suspend fun getGenreMovies(@Query("api_key") api_key: String): Response<ResponseGenre>

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int,
                               @Query("api_key") api_key: String,
                               @Query("append_to_response") append_to_response: String): Response<Movie>


    companion object {
        operator fun invoke(): MoviesApi {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .client(okHttpClient)
                    .build()
                    .create(MoviesApi::class.java)
        }
    }
}