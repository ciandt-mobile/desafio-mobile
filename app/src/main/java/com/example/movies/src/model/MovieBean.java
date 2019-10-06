package com.example.movies.src.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieBean {

    private JsonObject basicMovieInfo;
    private JsonObject detailedMovieInfo;
    private JsonObject configs;
    private boolean isFavourite;
    private List<PersonBean> actors;

    private Date releaseDate;

    public MovieBean(JsonObject info, JsonObject configs) {
        this.basicMovieInfo = info;
        this.configs = configs;
        this.isFavourite = false;
    }

    public JsonObject getDetailedMovieInfo() {
        return detailedMovieInfo;
    }

    public String getVideoKey() {
        if (detailedMovieInfo == null || detailedMovieInfo.get("videos").getAsJsonObject().get("results").getAsJsonArray().size() == 0) {
            return null;
        }
        return detailedMovieInfo.get("videos").getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("key").getAsString();
    }

    public JsonObject getConfigs() {
        return configs;
    }

    public void setDetailedMovieInfo(JsonObject info) {
        detailedMovieInfo = info;
        actors= new ArrayList<PersonBean>();
        for (JsonElement el : info.get("credits").getAsJsonObject().get("cast").getAsJsonArray()) {
            JsonObject actor = el.getAsJsonObject();
            PersonBean person = new PersonBean();
            person.setName(actor.get("name").getAsString());
            person.setCharacter(actor.get("character").getAsString());
            if (actor.get("profile_path") != null && !"null".equals(actor.get("profile_path").toString())) {
                person.setImgRUL(configs.get("images").getAsJsonObject().get("base_url").getAsString().replaceAll("http:", "https:")
                        + "h632"
                        + actor.get("profile_path").getAsString());
            }
            actors.add(person);
        }
    }

    public List<PersonBean> getCast() {
        return actors;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setIsfavourite(boolean b) {
        isFavourite = b;
    }

    public String getOverview() {
        return "";
    }

    public String getRunTime() {
        String runTime = null;
        if (detailedMovieInfo != null) {
            runTime = detailedMovieInfo.get("runtime").getAsString();
        }
        return runTime;
    }

    public String getID() {
        return basicMovieInfo.get("id").getAsString();
    }

    public Date getReleaseDate() {
        if (releaseDate == null) {
            try {
                releaseDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(basicMovieInfo.get("release_date").getAsString()).getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return releaseDate;
    }

    public String getReleaseDateToString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        return df.format(getReleaseDate());
    }

    public String getPosterURL() {
        String url = configs.get("images").getAsJsonObject().get("base_url").getAsString()
                + configs.get("images").getAsJsonObject().get("poster_sizes").getAsJsonArray().get(4).getAsString()
                + basicMovieInfo.get("poster_path").getAsString();
        url = url.replaceAll("http:", "https:");
        return url;
    }

    public String getOriginalPosterURL() {
        String url = configs.get("images").getAsJsonObject().get("base_url").getAsString()
                + "w1280"/*configs.get("images").getAsJsonObject().get("backdrop_sizes").getAsJsonArray().get(posterPos).getAsString()*/
                + basicMovieInfo.get("backdrop_path").getAsString();
        url = url.replaceAll("http:", "https:");
        return url;
    }

    public String getName() {
        return basicMovieInfo.get("title").getAsString();
    }

    public String getMovieYear() {
        return new SimpleDateFormat("yyyy").format(getReleaseDate());
    }

    public String getGenresNames() {
        StringBuffer sb = new StringBuffer();
        for (JsonElement el : detailedMovieInfo.get("genres").getAsJsonArray()) {
            sb.append(el.getAsJsonObject().get("name").getAsString()).append(", ");
        }
        return sb.toString().substring(0, sb.length()-2);
    }

    public String getMovieOverview() {
        return detailedMovieInfo.get("overview").getAsString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ReleaseDate: " + releaseDate)
                .append(" Name: ").append(getName());

        return sb.toString();
    }

    @Override
    public boolean equals(Object m) {
        if (m instanceof MovieBean) {
            if (getID().equals(((MovieBean) m).getID())) {
                return true;
            }
        }
        return false;
    }
}
