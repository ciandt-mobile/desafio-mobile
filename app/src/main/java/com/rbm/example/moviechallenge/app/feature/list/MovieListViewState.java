package com.rbm.example.moviechallenge.app.feature.list;

import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.data.data.MovieListData;

import java.util.ArrayList;
import java.util.List;

public class MovieListViewState {

    private List<MovieData> movieList;
    private boolean isLoading;

    public MovieListViewState() {
        this.movieList = new ArrayList<>();
        this.isLoading = false;
    }

    public List<MovieData> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieData> movieList) {
        this.movieList = movieList;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
