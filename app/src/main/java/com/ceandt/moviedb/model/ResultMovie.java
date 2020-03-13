package com.ceandt.moviedb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultMovie {

    @SerializedName("results")
    List<Movie> movies;

}
