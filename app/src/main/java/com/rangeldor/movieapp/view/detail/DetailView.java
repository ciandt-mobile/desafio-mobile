package com.rangeldor.movieapp.view.detail;

import com.rangeldor.movieapp.model.Movie;

import java.util.List;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setResult(Movie.Result result);
    void setDetail(Movie.Detail detail);
    void onErrorLoading(String message);

}
