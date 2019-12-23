package com.ciet.leogg.filmes.presenter;

import com.ciet.leogg.filmes.model.Cast;
import com.ciet.leogg.filmes.model.Movie;

import java.util.List;

public interface MoviesContract {
    interface DetailsView{
        void showMovie(Movie movie, List<Cast> castList,String genres);
    }
    interface ListView{
        void showMovies(List<Movie> movies);
    }
    interface TabInteraction {
        void loadMovies();
        void loadMoviesAndFilter();
    }
    interface MovieSelection {
        void loadMovie(Movie movie);
    }
}
