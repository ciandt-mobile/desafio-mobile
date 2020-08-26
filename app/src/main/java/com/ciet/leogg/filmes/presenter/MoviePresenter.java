package com.ciet.leogg.filmes.presenter;

import android.text.TextUtils;
import androidx.arch.core.util.Function;
import androidx.lifecycle.*;
import com.ciet.leogg.filmes.model.Cast;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.repository.MainRepository;

import java.util.ArrayList;
import java.util.List;

public class MoviePresenter extends ViewModel implements MoviesContract.MovieSelection {
    private final LiveData<List<Cast>> castList;
    private final LiveData<Movie> movie;
    
    private final LiveData<String> cast;
    private final LiveData<String> genres;
    private final LiveData<String> backdropPath;

    public MoviePresenter() {
        castList = MainRepository.getInstance().getCastList();
        movie = MainRepository.getInstance().getMovie();
        
        cast = Transformations.map(castList, new Function<List<Cast>, String>() {
            @Override
            public String apply(List<Cast> input) {
                List<String> castListString = new ArrayList<>();
                for(Cast cast : castList.getValue()){
                    castListString.add(cast.getName());
                }
             return TextUtils.join(", ",castListString);
            }
        });
        genres = Transformations.map(movie, new Function<Movie, String>() {
            @Override
            public String apply(Movie input) {
                return TextUtils.join(", ",movie.getValue().getGenres());
            }
        });
        backdropPath = Transformations.map(movie, new Function<Movie, String>() {
            @Override
            public String apply(Movie input) {
                return movie.getValue().getBackdropPath() == null
                        ?"https://via.placeholder.com/900x600/bcaaa4/bcaaa4.png"
                        :"https://image.tmdb.org/t/p/original"+movie.getValue().getBackdropPath();
            }
        });

    }

    @Override
    public void loadMovie(final Movie inMovie) {
        MainRepository.getInstance().refreshMovie(inMovie);
    }
        
    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<String> getCast() {
        return cast;
    }

    public LiveData<String> getGenres() {
        return genres;
    }

    public LiveData<String> getBackdropPath() {
        return backdropPath;
    }
}
