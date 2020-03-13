package com.ceandt.moviedb;

import com.ceandt.moviedb.arch.presenter.MovieDbPresenter;
import com.ceandt.moviedb.arch.presenter.MovieDbPresenterImpl;
import com.ceandt.moviedb.arch.view.MoviesView;
import com.ceandt.moviedb.model.Movie;
import com.ceandt.moviedb.preferences.Preferences;
import com.ceandt.moviedb.service.MovieDbService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import io.reactivex.Observable;

import static org.mockito.Mockito.times;

public class PresenterImplTest {

    private MovieDbPresenter mPresenter;

    @Before
    public void before() {
        MovieDbService service = Mockito.mock(MovieDbService.class);
        MoviesView mock = Mockito.mock(MoviesView.class);
        mPresenter = Mockito.mock(MovieDbPresenterImpl.class);
        mPresenter.setView(mock);
        Mockito.when(service.getPopularMovies()).thenReturn(Observable.just(Collections.singletonList(new Movie())));
    }

    @Test
    public void shouldBeOkWhenLoadMoviesIsCalled() {
        mPresenter.loadMovies(Preferences.POPULAR_VALUE);
        Mockito.verify(mPresenter, times(1)).loadMovies(Preferences.POPULAR_VALUE);
    }

    @Test
    public void shouldBeOkWhenSwitchPreferenceIsCalled() {
        mPresenter.switchPreference(Preferences.UPCOMING_VALUE);
        Mockito.verify(mPresenter, times(1)).switchPreference(Preferences.UPCOMING_VALUE);
    }

    @Test
    public void shouldBeOkWhenLoadMovieDetailIsCalled() {
        mPresenter.loadMovieDetail(1);
        Mockito.verify(mPresenter, times(1)).loadMovieDetail(1);
    }

}