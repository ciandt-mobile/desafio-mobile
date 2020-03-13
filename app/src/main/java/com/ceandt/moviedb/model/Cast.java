package com.ceandt.moviedb.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cast {

    @SerializedName("cast_id")
    private Integer castId;
    @SerializedName("character")
    private String character;
    @SerializedName("credit_id")
    private String creditId;
    @SerializedName("gender")
    private Integer gender;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("order")
    private Integer order;
    @SerializedName("profile_path")
    private String profilePath;

}
