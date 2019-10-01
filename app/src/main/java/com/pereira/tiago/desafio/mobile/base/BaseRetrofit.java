package com.pereira.tiago.desafio.mobile.base;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pereira on 25/06/2018.
 */

public class BaseRetrofit {

    private static Retrofit base;

    public static Retrofit getBaseRetrofit(Context context) {

        int cacheSize = 100 * 1024 * 1024; // 100 MB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        if (base == null) {
            Gson dateFormat = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            OkHttpClient client = new OkHttpClient.Builder()
                    .cache(cache)
                    .readTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request().newBuilder()
                                            .addHeader("Content-Type", "application/json")
                                            .addHeader("Accept", "application/json")
                                            .build();
                                    return chain.proceed(request);
                                }
                            }
                    )
                    .build();

            base = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(dateFormat))
                    .client(client)
                    .build();
        }

        return base;
    }

    public static void clearBaseRetrofit(){
        base = null;
    }
}
