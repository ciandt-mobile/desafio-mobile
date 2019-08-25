package com.svmdev.appmovies.data.model;

import com.google.gson.annotations.SerializedName;
import com.svmdev.appmovies.data.webservice.URLs;

import java.util.List;

public class MovieDetail {

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("title")
    private String title;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("release_date")
    private String realeseDate;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    public String getBackdropPathFormated() {
        if (backdropPath == null){
            return "";
        }
        String replace = backdropPath.replace("'\'", "");
        return URLs.imageUrl + replace;
    }

    public String getPosterPathFormated() {
        String replace = posterPath.replace("'\'", "");
        return URLs.imageUrl + replace;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getListedGenres() {
        if (genres.isEmpty()){
            return "Nenhum gênero disponível";
        }
        StringBuilder genres = new StringBuilder("| " + this.genres.get(0).getName());

        for (int i = 1; i < this.genres.size(); i++){
            genres.append(", ").append(this.genres.get(i).getName());
        }

        return genres.toString();
    }

    public String getRealeseDateYear() {
        return realeseDate.substring(0, 4);
    }

    public int getRuntime() {
        return runtime;
    }

    public String getOverview() {
        return overview;
    }
}
