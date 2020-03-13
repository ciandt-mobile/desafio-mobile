package com.ceandt.moviedb.injection.module;

import com.ceandt.moviedb.repository.MovieDbRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieDbNetworkModule {

    private static final int TIME_OUT = 15000;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY_PARAM = "api_key";
    private static final String API_KEY = "da80aa5bcc782d7314104a9fba3643c7";
    private static final String LANGUAGE_PARAM = "language";
    private static final String LANGUAGE_PARAM_VALUE = "pt-BR";

    @Provides
    @Singleton
    public static MovieDbRepository providesService(Retrofit retrofit) {
        return retrofit.create(MovieDbRepository.class);
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .build();
    }

    @Singleton
    @Provides
    public Interceptor providesInterceptor() {
        return chain -> {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            HttpUrl url = request.url();
            url = url.newBuilder()
                    .addQueryParameter(API_KEY_PARAM, API_KEY)
                    .addQueryParameter(LANGUAGE_PARAM, LANGUAGE_PARAM_VALUE)
                    .build();
            request = requestBuilder.url(url).build();
            return chain.proceed(request);
        };
    }

}
