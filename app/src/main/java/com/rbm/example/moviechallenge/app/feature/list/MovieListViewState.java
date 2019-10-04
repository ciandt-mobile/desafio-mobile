package com.rbm.example.moviechallenge.app.feature.list;

import androidx.lifecycle.MutableLiveData;

import com.rbm.example.moviechallenge.data.data.MovieListData;

public class MovieListViewState {

    private MutableLiveData<MovieListData> movieList;
    private MutableLiveData<Boolean> isLoading;

    public MovieListViewState() {
        this.movieList = new MutableLiveData<>();
        this.isLoading = new MutableLiveData<Boolean>(false);
    }

    public MutableLiveData<MovieListData> getMovieList() {
        return movieList;
    }

    public void setMovieList(MutableLiveData<MovieListData> movieList) {
        this.movieList = movieList;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(MutableLiveData<Boolean> isLoading) {
        this.isLoading = isLoading;
    }
}
