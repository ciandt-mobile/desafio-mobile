package com.pereira.tiago.desafio.mobile.main;

import android.content.Context;

import com.pereira.tiago.desafio.mobile.databasemodels.Movie;

import java.util.List;

public interface Contract {
    interface MainModel{
        void getMoviesPopularNetwork(int currentPage);
        void getMoviesPopularDatabase();
        void getMoviesUpcomingDatabase();
    }

    interface MainPresenter {
        Context getContext();
        void setView(MainView view);
        void getListMoviesPopular(int currentPage);
        void getListMoviesUpcoming(int currentPage);
        void setMovieList(List<Movie> movieList, String option);
        void changeOption(String option, int currentPage);
    }

    interface MainView {
        void showNoResults();
        void showButtonPopular();
        void showButtonUpcoming();
        void setPopulateRecycler(List<Movie> movies, String option);
    }
}
