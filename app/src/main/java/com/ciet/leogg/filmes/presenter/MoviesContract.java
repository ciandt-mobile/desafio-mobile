package com.ciet.leogg.filmes.presenter;

import com.ciet.leogg.filmes.model.Cast;
import com.ciet.leogg.filmes.model.Genre;
import com.ciet.leogg.filmes.model.Movie;

import java.util.List;

public interface MoviesContract {
    interface DetailsView{
        void showMovie(Movie movie, List<Cast> castList,List<Genre> genreList);
    }
    interface ListView{
        void showMovies(List<Movie> movies);
    }
    interface TabInteraction {
        void setPopularView(ListView view);
        void setUpcomingView(ListView view);
        void loadMovies();
        void loadMoviesAndFilter();
    }
    interface MovieSelection {
        void setDetailsView(DetailsView view);
        void loadMovie(Movie movie);
    }
}
