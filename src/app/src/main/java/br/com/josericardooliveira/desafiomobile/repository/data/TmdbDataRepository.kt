package br.com.josericardooliveira.desafiomobile.repository.data

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object TmdbDataRepository {

    private const val baseUrl: String = "https://api.themoviedb.org/3/"

    private val client = OkHttpClient.Builder().addInterceptor(QueryInterceptor()).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TmdbDataService::class.java)
}

class QueryInterceptor : Interceptor {

    companion object {
        private const val apiKey = "5594024337e33b09518167c3bc8277a6"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language", Locale.getDefault().toLanguageTag())
            .build()

        // Request customization: add request headers
        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}