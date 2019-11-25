package br.com.ciandt.application.fellini.domain.crew;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credit {

    @SerializedName("id")
    private int id;
    @SerializedName("cast")
    private List<Actor> cast;
    @SerializedName("crew")
    private List<Actor> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public List<Actor> getCrew() {
        return crew;
    }

    public void setCrew(List<Actor> crew) {
        this.crew = crew;
    }
}
