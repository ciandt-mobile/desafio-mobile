package com.pereira.tiago.desafio.mobile.main;

import android.content.Context;

import com.pereira.tiago.desafio.mobile.databasemodels.Movie;

import java.util.List;

public interface Contract {
    interface MainModel{
        void getMoviesPopularNetwork();
        void getMoviesPopularDatabase();
    }

    interface MainPresenter {
        Context getContext();
        void getPermissions();
        void setView(MainView view);
        void getListMoviesPopular();
        void setMovieList(List<Movie> movieList);
    }

    interface MainView {
        void showToast( String message );
        void showNoResults();
        void setPopulateRecycler(List<Movie> movies);
    }
}
