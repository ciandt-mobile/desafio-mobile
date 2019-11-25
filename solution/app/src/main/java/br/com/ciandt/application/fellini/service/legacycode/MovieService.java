package br.com.ciandt.application.fellini.service.legacycode;

import br.com.ciandt.application.fellini.domain.movie.Movie;
import br.com.ciandt.application.fellini.domain.movie.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/latest")
    Call<MovieResponse> getLatestMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_path}")
    Call<MovieResponse> getMoviesFromPath(@Path("movie_path") String desiredPath, @Query("api_key") String apiKey);

    @GET("movie/top_rated/{page_index}")
    Call<MovieResponse> getTopRatedMoviesFromIndex(@Path("page_index") int pageIndex, @Query("api_key") String apiKey);
}
