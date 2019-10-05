package com.rbm.example.moviechallenge.data.data;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class MovieDetailData extends MovieData {

    @SerializedName("genres")
    private Map<Integer, String> genres;

    @SerializedName("runtime")
    private Integer runtime;

    /*@SerializedName("belongs_to_collection")
    private String belongs_to_collection;

    @SerializedName("budget")
    private String budget;

    @SerializedName("homepage")
    private String homepage;*/


}
