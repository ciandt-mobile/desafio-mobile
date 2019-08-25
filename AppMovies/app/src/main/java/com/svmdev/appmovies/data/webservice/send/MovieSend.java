package com.svmdev.appmovies.data.webservice.send;

import com.google.gson.annotations.SerializedName;

public class MovieSend {

    @SerializedName("api_key")
    public String apiKey;

    @SerializedName("language")
    public String language;

    @SerializedName("page")
    public int page;

}
