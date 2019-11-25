package br.com.ciandt.application.fellini.service.callbacks;

import br.com.ciandt.application.fellini.domain.movie.Movie;

public interface OnGettingMovieDetailsCallback {

    void onSuccess(Movie movie);

    void onError();

}
