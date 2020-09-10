package com.ciet.leogg.filmes.presenter;

import com.ciet.leogg.filmes.model.Movie;

import java.util.List;

public interface MoviesContract {
    interface HomeView{}
    interface HomeInteraction{
        void setHomeView(HomeView view);
    }
    interface ListView{
        void showMovies(List<Movie> movies);
    }
    interface TabInteraction {
        void setPopularView(ListView view);
        void setUpcomingView(ListView view);
        void loadMovies();
        void loadMoviesAndFilter();
        void more();
        void less();
    }
    interface MovieSelection {
        void loadMovie(Movie movie);
    }
}
