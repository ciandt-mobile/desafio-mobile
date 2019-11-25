package br.com.ciandt.application.fellini.domain.movie;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    private int id;

    @SerializedName("site")
    private String site;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
