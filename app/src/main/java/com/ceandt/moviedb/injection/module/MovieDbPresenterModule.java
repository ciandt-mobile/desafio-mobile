package com.ceandt.moviedb.injection.module;

import com.ceandt.moviedb.arch.presenter.MovieDbPresenter;
import com.ceandt.moviedb.arch.presenter.MovieDbPresenterImpl;
import com.ceandt.moviedb.service.MovieDbService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class MovieDbPresenterModule {

    @Provides
    @Singleton
    public MovieDbPresenter providesPresenter(final MovieDbService service) {
        return new MovieDbPresenterImpl(service);
    }

}
