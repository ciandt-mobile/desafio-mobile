package br.com.victoroliveira.cit.di

import br.com.victoroliveira.cit.BuildConfig
import br.com.victoroliveira.cit.data.remote.ApiService
import br.com.victoroliveira.cit.data.repository.MovieRepositoryImp
import br.com.victoroliveira.cit.domain.MovieRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    single { createService(get()) }
    single { createRetrofit(get(), BuildConfig.BASE_URL) }
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor { chain -> createParametersDefault(chain) }
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createParametersDefault(chain: Interceptor.Chain): Response {
    var request = chain.request()
    val builder = request.url().newBuilder()

    builder.addQueryParameter("api_key", BuildConfig.API_PRIVATE)
    request = request.newBuilder().url(builder.build()).build()
    return chain.proceed(request)
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createMovieRepository(apiService: ApiService): MovieRepository {
    return MovieRepositoryImp(apiService)
}