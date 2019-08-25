package com.svmdev.appmovies.data.model;

import com.google.gson.annotations.SerializedName;
import com.svmdev.appmovies.data.webservice.URLs;

public class Cast {

    @SerializedName("character")
    private String character;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profilePath;

    @SerializedName("gender")
    private int gender;

    public Cast(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        if (profilePath == null){
            return "";
        }
        String replace = profilePath.replace("'\'","");
        return URLs.imageUrl + replace;
    }

    public int getGender() {
        return gender;
    }
}
