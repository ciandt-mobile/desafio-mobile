package com.svmdev.appmovies.data.webservice;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static final long DEFAULT_TIMEOUT_SECONDS = 15L;
    private final Retrofit retrofit;

    public RetrofitConfig() {
        String BASE_URL = "http://www.google.com.br";
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging)
                .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT_SECONDS,TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT_SECONDS,TimeUnit.SECONDS);

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

    }

    public API getAPI(){
        return this.retrofit.create(API.class);
    }

}
