package com.ciet.leogg.filmes.presenter;

import androidx.lifecycle.*;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.repository.MainRepository;

import java.util.List;

public class TabPresenter extends ViewModel implements MoviesContract.TabInteraction {
    private final LiveData<List<Movie>> popularMoviesList;
    private final LiveData<List<Movie>> upcomingMoviesList;
    private MoviesContract.ListView popularView;
    private MoviesContract.ListView upcomingView;

    public TabPresenter() {
        popularMoviesList = MainRepository.getInstance().getPopularMovieList();
        upcomingMoviesList = MainRepository.getInstance().getUpcomingMovieList();
    }

    @Override
    public void setPopularView(MoviesContract.ListView view) {
        this.popularView = view;
        popularMoviesList.observe((LifecycleOwner) popularView, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                popularView.showMovies(movies);
            }
        });
    }

    @Override
    public void setUpcomingView(MoviesContract.ListView view) {
        this.upcomingView = view;
        upcomingMoviesList.observe((LifecycleOwner) upcomingView, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                upcomingView.showMovies(movies);
            }
        });
    }

    @Override
    public void loadMovies() {
        MainRepository.getInstance().refreshMovieList();
    }

    @Override
    public void loadMoviesAndFilter() {
        MainRepository.getInstance().refreshMovieList();
    }


}
