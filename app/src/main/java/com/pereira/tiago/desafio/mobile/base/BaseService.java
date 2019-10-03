package com.pereira.tiago.desafio.mobile.base;

import com.pereira.tiago.desafio.mobile.reponse.ApiResponse;
import com.pereira.tiago.desafio.mobile.reponse.ApiResponseCredits;
import com.pereira.tiago.desafio.mobile.reponse.ApiResponseDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("{movie_id}?")
    Call<ApiResponseDetails> getMovieDetails(
            @Path("movie_id") String movie_id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("{movie_id}/credits?")
    Call<ApiResponseCredits> getCredits(
            @Path("movie_id") String movie_id,
            @Query("api_key") String apiKey
    );
}
