package com.ceandt.moviedb.injection.module;

import com.ceandt.moviedb.repository.MovieDbRepository;
import com.ceandt.moviedb.service.MovieDbService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDbServiceModule {

    @Provides
    @Singleton
    public MovieDbService provideServiceModule(final MovieDbRepository repository) {
        return new MovieDbService(repository);
    }

}
