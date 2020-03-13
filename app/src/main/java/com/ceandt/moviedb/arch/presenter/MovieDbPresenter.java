package com.ceandt.moviedb.arch.presenter;

import com.ceandt.moviedb.arch.view.DetailMovieView;
import com.ceandt.moviedb.arch.view.MoviesView;

public interface MovieDbPresenter {

    void loadMovies(final String type);

    void switchPreference(final String type);

    void setView(MoviesView view);

    void setView(DetailMovieView view);

    void loadMovieDetail(Integer movieId);

    void dispose();


}
