package com.ciet.leogg.filmes.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.ciet.leogg.filmes.model.Cast;
import com.ciet.leogg.filmes.model.Genre;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.repository.MainRepository;

import java.util.List;

public class MoviePresenter extends ViewModel implements MoviesContract.MovieSelection {
    private MoviesContract.DetailsView detailsView;
    private final LiveData<List<Cast>> castList;
    private final LiveData<List<Genre>> genreList;
    private final LiveData<Movie> movie;

    public MoviePresenter() {
        castList = MainRepository.getInstance().getCastList();
        genreList = MainRepository.getInstance().getGenreList();
        movie = MainRepository.getInstance().getMovie();
    }

    @Override
    public void setDetailsView(MoviesContract.DetailsView view) {
        this.detailsView = view;
    }

    @Override
    public void loadMovie(final Movie inMovie) {
        MainRepository.getInstance().refreshMovie(inMovie);
        movie.observe((LifecycleOwner) detailsView, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                detailsView.showMovie(movie,castList.getValue(),genreList.getValue());
            }
        });
    }


}
