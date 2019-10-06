package com.example.movies.src.api;

import android.view.View;

import com.example.movies.R;
import com.example.movies.src.model.MovieBean;
import com.example.movies.src.utils.Logger;
import com.example.movies.src.utils.Requests;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieAPI {

    private static final String API_KEY = "api_key=288e747476df3c7ef804c9b26ff8b7b1";
    private static final String END_POINT = "https://api.themoviedb.org/3/";
    private static final String TAG = "MovieAPI";
    private static MovieAPI inst;

    private Requests client;
    private Configurations imgConfig;
    private String language;
    private String region;

    public static MovieAPI getInstance() {
        if (inst == null) {
            inst = new MovieAPI();
        }
        return inst;
    }

    private MovieAPI() {
        client = new Requests();
        language = Locale.getDefault().toLanguageTag().toString();
        region = language.contains("-") ? language.substring(language.indexOf("-")+1).toUpperCase() : "US";
        Logger.log(TAG, language + "  " + region);
        imgConfig = new Configurations();
    }

    public List<MovieBean> getPopularMovies(int page) {
        try {
            List<MovieBean> list = new ArrayList<MovieBean>();
            String url = END_POINT + "discover/movie?sort_by=popularity.desc&language=" + language + "&region=" + region + "&page=" + page + "&" + API_KEY;
            JsonObject rsp = new Gson().fromJson(client.makeGet(url), JsonObject.class);
            if (rsp.get("results") != null) {
                for (JsonElement el : rsp.get("results").getAsJsonArray()) {
                    list.add(new MovieBean(el.getAsJsonObject(), imgConfig.getConfigs()));
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject getConfigurations() {
        return imgConfig.getConfigs();
    }

    public JsonObject getMovieDetails(String id) {
        try {
            String url = END_POINT + "movie/" + id + "?append_to_response=videos,credits&language=" + language + "&region=" + region + "&" + API_KEY;
            JsonObject rsp = new Gson().fromJson(client.makeGet(url), JsonObject.class);
            Logger.log(TAG, rsp.toString());
            return rsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class Configurations {

        private JsonObject imgConfiguration;

        public Configurations() {
            try {
                String url = END_POINT + "configuration?" + API_KEY;
                imgConfiguration = new Gson().fromJson(client.makeGet(url), JsonObject.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public JsonObject getConfigs() {
            return imgConfiguration;
        }
    }
}
