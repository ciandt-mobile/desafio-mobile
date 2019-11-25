package br.com.ciandt.application.fellini.service;

import br.com.ciandt.application.fellini.BuildConfig;
import br.com.ciandt.application.fellini.domain.crew.Credit;
import br.com.ciandt.application.fellini.domain.movie.GenreResponse;
import br.com.ciandt.application.fellini.domain.movie.Movie;
import br.com.ciandt.application.fellini.domain.movie.MovieResponse;
import br.com.ciandt.application.fellini.service.apiconstants.APIConstants;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingCreditsCallback;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingGenresCallback;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingMovieDetailsCallback;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingMoviesCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    public static final String UPCOMING = "upcoming";
    public static final String NOW_PLAYING = "now_playing";

    public static final String LANGUAGE = APIConstants.LANGUAGE_EN_US;

    private static MovieRepository movieRepo;
    private TheMovieDataBaseAPI api;

    public MovieRepository(TheMovieDataBaseAPI api) {
        this.api = api;
    }

    public static MovieRepository getInstance() {
        if (movieRepo == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(APIConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            movieRepo = new MovieRepository(retrofit.create(TheMovieDataBaseAPI.class));
        }

        return movieRepo;
    }

    public void getMovies(int page, String sortBy, final OnGettingMoviesCallback callback) {

        Callback<MovieResponse> call = new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null && movieResponse.getResults() != null) {
                        callback.onSuccess(movieResponse.getPage(), movieResponse.getResults());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onError();
            }
        };

        switch (sortBy) {
            case TOP_RATED:
                api.getTopRatedMovies(BuildConfig.THE_MOVIE_DATABASE_API_TOKEN, LANGUAGE, page).enqueue(call);
                break;

            case UPCOMING:
                api.getUpcomingMovies(BuildConfig.THE_MOVIE_DATABASE_API_TOKEN, LANGUAGE, page).enqueue(call);
                break;

            case NOW_PLAYING:
                api.getNowPlayingMovies(BuildConfig.THE_MOVIE_DATABASE_API_TOKEN, LANGUAGE, page).enqueue(call);
                break;

            case POPULAR:
                api.getPopularMovies(BuildConfig.THE_MOVIE_DATABASE_API_TOKEN, LANGUAGE, page).enqueue(call);
                break;
        }
    }

    public void getGenres(final OnGettingGenresCallback callback) {
        api.getGenres(BuildConfig.THE_MOVIE_DATABASE_API_TOKEN, LANGUAGE).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.isSuccessful()) {
                    GenreResponse genreResponse = response.body();
                    if (genreResponse != null && genreResponse.getGenres() != null) {
                        callback.onSuccess(genreResponse.getGenres());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                callback.onError();
            }
        });

    }

    public void getMovieDetails(int movieId, final OnGettingMovieDetailsCallback callback) {
        api.getMovieDetails(movieId, BuildConfig.THE_MOVIE_DATABASE_API_TOKEN, LANGUAGE).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();
                    if (movie != null) {
                        callback.onSuccess(movie);
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public void getMovieCredits(int movieId, final OnGettingCreditsCallback callback) {
        api.getMovieCredits(movieId, BuildConfig.THE_MOVIE_DATABASE_API_TOKEN).enqueue(new Callback<Credit>() {
            @Override
            public void onResponse(Call<Credit> call, Response<Credit> response) {
                if (response.isSuccessful()) {
                    Credit credit = response.body();
                    if (credit != null) {
                        callback.onSuccess(credit);
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Credit> call, Throwable t) {
                callback.onError();
            }
        });
    }

    public void searchFor(String query, boolean includeAdult, final OnGettingMoviesCallback callback) {
        api.searchFor(BuildConfig.THE_MOVIE_DATABASE_API_TOKEN, LANGUAGE, query, includeAdult).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null && movieResponse.getResults() != null) {
                        callback.onSuccess(movieResponse.getPage(), movieResponse.getResults());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }

}
