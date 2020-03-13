package com.ceandt.moviedb.injection.component;


import com.ceandt.moviedb.activity.DetailMovieActivity;
import com.ceandt.moviedb.activity.MainActivity;
import com.ceandt.moviedb.injection.module.MovieDbNetworkModule;
import com.ceandt.moviedb.injection.module.MovieDbPresenterModule;
import com.ceandt.moviedb.injection.module.MovieDbServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MovieDbNetworkModule.class,
        MovieDbServiceModule.class,
        MovieDbPresenterModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailMovieActivity detailMovieActivity);

}
