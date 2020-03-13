package com.ceandt.moviedb.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Genre {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

}
