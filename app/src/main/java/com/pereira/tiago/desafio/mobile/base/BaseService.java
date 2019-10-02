package com.pereira.tiago.desafio.mobile.base;

import com.pereira.tiago.desafio.mobile.reponse.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseService {

    @GET("popular?")
    Call<ApiResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") String page);

    @GET("upcoming?")
    Call<ApiResponse> getUpcomingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") String page
    );
}
