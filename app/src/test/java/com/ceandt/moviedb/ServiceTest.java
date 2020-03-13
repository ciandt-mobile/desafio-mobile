package com.ceandt.moviedb;

import com.ceandt.moviedb.model.DetailMovie;
import com.ceandt.moviedb.model.ResultMovie;
import com.ceandt.moviedb.repository.MovieDbRepository;
import com.ceandt.moviedb.service.MovieDbService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import io.reactivex.Observable;

import static org.junit.Assert.assertNotNull;

public class ServiceTest {

    private MovieDbService mService;

    @Before
    public void before() {
        MovieDbRepository repository = Mockito.mock(MovieDbRepository.class);
        mService = new MovieDbService(repository);
        mockReturn(repository.getUpComing());
        mockReturn(repository.getPopular());
        mockReturn(repository.getNowPlaying());
        mockReturn(repository.getTopRated());
        Mockito.when(repository.getDetail(1)).thenReturn(Observable.just(new DetailMovie()));
    }

    private void mockReturn(Observable<ResultMovie> methodCall) {
        Mockito.when(methodCall).thenReturn(Observable.just(new ResultMovie()));
    }

    @Test
    public void shouldBeNotNullWhenGetUpComingMovies() {
        assertNotNull(mService.getUpComingMovies());
    }

    @Test
    public void shouldBeNotNullWhenGetPopularMovies() {
        assertNotNull(mService.getPopularMovies());
    }

    @Test
    public void shouldBeNotNullWhenGetNowPlayingMovies() {
        assertNotNull(mService.getNowPlayingMovies());
    }

    @Test
    public void shouldBeNotNullWhenGetTopRatedMovies() {
        assertNotNull(mService.getTopRateMovies());
    }

    @Test
    public void shouldBeNotNullWhenGetDetailMovie() {
        assertNotNull(mService.getDetailMovie(1));
    }

}