package com.ciet.leogg.filmes.presenter;

import androidx.arch.core.util.Function;
import androidx.lifecycle.*;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.repository.MainRepository;

public class HomePresenter extends ViewModel implements MoviesContract.HomeInteraction {
    private final LiveData<Integer> page;
    private final LiveData<Movie> movie;
    private final LiveData<String> posterPath;

    public HomePresenter(){
        page = MainRepository.getInstance().getPage();
        movie = MainRepository.getInstance().getMovie();

        posterPath = Transformations.map(movie,new Function<Movie,String>(){
            @Override
            public String apply(Movie input) {
                return input.getPosterPath() == null
                        ?"https://via.placeholder.com/600x900/bcaaa4/bcaaa4.png"
                        :"https://image.tmdb.org/t/p/w500"+input.getPosterPath();
            }
        });
    }

    @Override
    public void setHomeView(MoviesContract.HomeView view) {
        posterPath.observe((LifecycleOwner) view, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //posterPath databinding doesn't work without an observer attached.
            }
        });
    }

    public LiveData<Integer> getPage() {
        return page;
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<String> getPosterPath(){
        return posterPath;
    }
}
