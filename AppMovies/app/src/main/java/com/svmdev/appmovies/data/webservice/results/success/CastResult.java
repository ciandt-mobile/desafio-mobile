package com.svmdev.appmovies.data.webservice.results.success;

import com.google.gson.annotations.SerializedName;
import com.svmdev.appmovies.data.model.Cast;

import java.util.List;

public class CastResult {

    @SerializedName("cast")
    public List<Cast> cast;

}
