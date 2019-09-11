package com.rangeldor.movieapp.home;

import com.rangeldor.movieapp.model.Movie;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setResults(List<Movie.Result> results);
    void onErrorLoading(String message);
}
