package com.ciet.leogg.filmes.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.repository.MainRepository;

public class HomePresenter extends ViewModel implements MoviesContract.HomeInteraction {
    private MoviesContract.HomeView homeView;
    private LiveData<Integer> page;
    private LiveData<Movie> movie;

    public HomePresenter(){
        page = MainRepository.getInstance().getPage();
        movie = MainRepository.getInstance().getMovie();
    }

    @Override
    public void setHomeView(MoviesContract.HomeView view) {
        this.homeView = view;
    }

    @Override
    public void loadPageAndLastMovie() {
        page.observe((LifecycleOwner) homeView, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                homeView.showPageAndLastMovie(integer,movie.getValue());
            }
        });
        movie.observe((LifecycleOwner) homeView, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                homeView.showPageAndLastMovie(page.getValue(),movie);
            }
        });
    }
}
