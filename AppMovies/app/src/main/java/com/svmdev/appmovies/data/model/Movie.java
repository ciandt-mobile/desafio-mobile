package com.svmdev.appmovies.data.model;

import com.google.gson.annotations.SerializedName;
import com.svmdev.appmovies.data.webservice.URLs;

public class Movie {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;
    
    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        if(posterPath == null) {
            return "";
        }
        String replace = posterPath.replace("'\'","");
        return URLs.imageUrl + replace;
    }

    public String getPoster() {
        return posterPath;
    }

    public String getFormatedReleaseDate() {
        String[] formatedDate = releaseDate.split("-");
        return formatedDate[2]+"/"+formatedDate[1]+"/"+formatedDate[0];
    }

    public int getId() {
        return id;
    }

}
