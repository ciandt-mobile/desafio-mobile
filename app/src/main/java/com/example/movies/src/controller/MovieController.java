package com.example.movies.src.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movies.R;
import com.example.movies.src.api.MovieAPI;
import com.example.movies.src.model.MovieBean;
import com.example.movies.src.utils.Logger;
import com.example.movies.src.view.DetailedMovieView;
import com.example.movies.src.view.MoviesAdapter;
import com.google.gson.JsonObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieController {

    private static String TAG = "MovieController";
    private MovieAPI api;
    private List<MovieBean> popularList;
    private List<MovieBean> upcomingList;
    private List<MovieBean> myFavouritesList;
    private AppCompatActivity activity;
    private MoviesAdapter popularAdapter;
    private MoviesAdapter upComingAdapter;
    private MoviesAdapter favouriteAdapter;
    private int moviePage;

    public MovieController(AppCompatActivity activity) {
        this.activity = activity;
        api = MovieAPI.getInstance();

        popularList = new ArrayList<MovieBean>(100);
        for (moviePage = 1; moviePage <= 5; moviePage++) {
            popularList.addAll(api.getPopularMovies(moviePage));
        }

        activity.findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        getUpcomingList();
        popularAdapter = new MoviesAdapter(activity,getPopularList(), this, MoviesAdapter.TYPE_POPULAR);
        getFavouritesList();
    }

    private List<MovieBean> getPopularList() {
        return popularList;
    }

    private List<MovieBean> getUpcomingList() {
        if (upcomingList == null || upcomingList.size() != popularList.size()) {
            upcomingList = new ArrayList<MovieBean>(popularList.size());
            upcomingList.addAll(popularList);
            Collections.sort(upcomingList, new Comparator<MovieBean>() {
                @Override
                public int compare(MovieBean m1, MovieBean m2) {
                    return m1.getReleaseDate().compareTo(m2.getReleaseDate());
                }
            });
        }
        Date today = new Date(System.currentTimeMillis());
        int firsIndex = -1;
        for (int i = 0; i < upcomingList.size(); i++) {
            if (upcomingList.get(i).getReleaseDate().after(today)) {
                firsIndex = i;
                break;
            }
        }
        List<MovieBean> list = new ArrayList<MovieBean>(upcomingList.size());
        if (firsIndex != -1) {
            list.addAll(upcomingList.subList(firsIndex, upcomingList.size()));
        }
        Logger.log(TAG, "UpcomingList: " + list.toString());
        return list;
    }

    private List<MovieBean> getFavouritesList() {
        if (myFavouritesList == null) {
            myFavouritesList = new ArrayList<MovieBean>();
            SharedPreferences sharedPreference = activity.getSharedPreferences("favMoviesIds", Context.MODE_PRIVATE);
            Set<String> ids = sharedPreference.getStringSet("favMoviesIds", new HashSet<String>());
            for (String id : ids) {
                if (!id.isEmpty()) {
                    MovieBean movie = new MovieBean(api.getMovieDetails(id), api.getConfigurations());
                    movie.getReleaseDate();
                    movie.setIsfavourite(true);
                    myFavouritesList.add(movie);
                }
            }
        }
        return myFavouritesList;
    }

    public void getNextMoviesPage() {
        popularList.addAll(api.getPopularMovies(++moviePage));
    }

    public JsonObject getDetailedMovieInformation(MovieBean m) {
        if (m.getRunTime() == null) {
            m.setDetailedMovieInfo(api.getMovieDetails(m.getID()));
            JsonObject obj = m.getDetailedMovieInfo();
        }
        return m.getDetailedMovieInfo();
    }

    public void addFavouriteMovie(MovieBean m) {
        SharedPreferences sharedPreference = activity.getSharedPreferences("favMoviesIds", Context.MODE_PRIVATE);
        Set<String> ids = sharedPreference.getStringSet("favMoviesIds", new HashSet<String>());
        ids.add(m.getID());
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putStringSet("favMoviesIds", ids);
        editor.commit();
        myFavouritesList.add(m);
    }

    public void removeFavouriteMovie(MovieBean m) {
        SharedPreferences sharedPreference = activity.getSharedPreferences("favMoviesIds", Context.MODE_PRIVATE);
        Set<String> ids = sharedPreference.getStringSet("favMoviesIds", new HashSet<String>());
        ids.remove(m.getID());
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putStringSet("favMoviesIds",ids);
        editor.commit();
        myFavouritesList.remove(m);
    }

    public void showPopularVideos() {
        Logger.log(TAG, "showPopularVideos");
        popularAdapter = new MoviesAdapter(activity,getPopularList(), this, MoviesAdapter.TYPE_POPULAR);
    }

    public void showUpcomingVideos() {
        Logger.log(TAG, "showUpcomingVideos");
        upComingAdapter = new MoviesAdapter(activity,getUpcomingList(), this, MoviesAdapter.TYPE_UPCOMING);
    }

    public void showFavouritesVideos() {
        Logger.log(TAG, "showFavouritesVideos");
        favouriteAdapter = new MoviesAdapter(activity, getFavouritesList(), this, MoviesAdapter.TYPE_FAVOURITE);
    }

    public void showDetailedMovieView(MovieBean movie) {
        Intent intent = new Intent(activity, DetailedMovieView.class);
        intent.putExtra("movie", getDetailedMovieInformation(movie).toString());
        intent.putExtra("config", movie.getConfigs().toString());
        activity.startActivity(intent);
    }

}
