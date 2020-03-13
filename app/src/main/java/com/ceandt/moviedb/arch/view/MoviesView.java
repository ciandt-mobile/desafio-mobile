package com.ceandt.moviedb.arch.view;

import com.ceandt.moviedb.model.Movie;

import java.util.List;

public interface MoviesView {

    void onLoadNowPlayingMovies(List<Movie> movies);

    void onLoadTopRatedMovies(List<Movie> movies);

    void onLoadUpComingOrPopularMovies(List<Movie> movies);

}
