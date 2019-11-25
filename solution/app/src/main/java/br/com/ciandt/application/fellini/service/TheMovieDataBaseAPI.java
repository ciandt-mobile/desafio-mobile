package br.com.ciandt.application.fellini.service;

import br.com.ciandt.application.fellini.domain.crew.Credit;
import br.com.ciandt.application.fellini.domain.movie.GenreResponse;
import br.com.ciandt.application.fellini.domain.movie.Movie;
import br.com.ciandt.application.fellini.domain.movie.MovieResponse;
import br.com.ciandt.application.fellini.service.apiconstants.APIConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDataBaseAPI {

    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}/credits")
    Call<Credit> getMovieCredits(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> searchFor(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String query, @Query("include_adult") boolean includeAdult);
}
