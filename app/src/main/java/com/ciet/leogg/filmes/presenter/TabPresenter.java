package com.ciet.leogg.filmes.presenter;

import com.android.volley.Response;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.api.JacksonListRequest;
import com.ciet.leogg.filmes.model.Movie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TabPresenter implements MoviesContract.TabInteraction {
    private static TabPresenter instance;
    private List<Movie> loadedMovies = new ArrayList<>();
    private MoviesContract.ListView popularView;
    private MoviesContract.ListView upcomingView;

    private TabPresenter() {
    }

    public static synchronized TabPresenter getInstance(){
        if(instance == null){
            instance = new TabPresenter();
        }
        return instance;
    }

    public TabPresenter setPopularView(MoviesContract.ListView popularView) {
        this.popularView = popularView;
        return this;
    }

    public TabPresenter setUpcomingView(MoviesContract.ListView upcomingView) {
        this.upcomingView = upcomingView;
        return this;
    }

    @Override
    public void loadMovies() {
        loadedMovies.clear();
        Response.Listener<List<Movie>>listener = new Response.Listener<List<Movie>>() {
            @Override
            public void onResponse(List<Movie> response) {
                loadedMovies.addAll(response);
                if(loadedMovies.size() > 40){
                    popularView.showMovies(response);
                }
            }
        };
        for(int i=1;i<=5;i++){
            AppRequestQueue.getInstance().addToRequestQueue(new JacksonListRequest<>("https://api.themoviedb.org/3/discover/movie?api_key=adda3de7d4f28a11095c260028a9a7ac&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page="+i,listener,Movie.class));
        }
    }

    @Override
    public void loadMoviesAndFilter() {
        //filtering the popular request as required.
        upcomingView.showMovies(filter(loadedMovies));
    }

    private List<Movie> filter(List<Movie> input){
        List<Movie> output = new ArrayList<>();
        for(Movie movie:input){
            if(movie.getReleaseDate().after(Calendar.getInstance().getTime())){
                output.add(movie);
            }
        }
        return output;
    }
}
