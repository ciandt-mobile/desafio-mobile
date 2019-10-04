package com.rbm.example.moviechallenge.data.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListData {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<MovieData> movieList;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieData> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieData> movieList) {
        this.movieList = movieList;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
