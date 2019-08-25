package com.svmdev.appmovies.data.webservice;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface API {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET(URLs.genreUrl)
    Call<JsonElement> getGenre();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET(URLs.popularUrl)
    Call<JsonElement> getPopular(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET(URLs.upcommingUrl)
    Call<JsonElement> getUpcomming(
               @Query("api_key") String apiKey,
               @Query("language") String language,
               @Query("page") int page);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET
    Call<JsonElement> getMovieDetails(
            @Url String url,
            @Query("api_key") String apiKey,
            @Query("language") String language);

}
