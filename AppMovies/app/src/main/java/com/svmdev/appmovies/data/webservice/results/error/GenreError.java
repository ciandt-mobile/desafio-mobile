package com.svmdev.appmovies.data.webservice.results.error;

import com.google.gson.annotations.SerializedName;

public class GenreError {

    @SerializedName("status_code")
    public int statusCode;

    @SerializedName("status_message")
    public String statusMessage;

    @SerializedName("success")
    public boolean success;

}
