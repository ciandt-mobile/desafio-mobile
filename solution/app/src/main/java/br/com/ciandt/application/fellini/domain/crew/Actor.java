package br.com.ciandt.application.fellini.domain.crew;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.ciandt.application.fellini.service.apiconstants.APIConstants;

public class Actor {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("character")
    private String character;
    @SerializedName("credit_id")
    private String creditId;
    @SerializedName("gender")
    private int gender;
    @SerializedName("profile_path")
    private String profilePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProfilePath() {
        return APIConstants.BASE_IMAGE_URL + profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
