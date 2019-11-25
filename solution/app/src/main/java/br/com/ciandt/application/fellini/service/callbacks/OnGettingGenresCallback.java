package br.com.ciandt.application.fellini.service.callbacks;

import java.util.List;

import br.com.ciandt.application.fellini.domain.movie.Genre;
import br.com.ciandt.application.fellini.domain.movie.GenreResponse;

public interface OnGettingGenresCallback {

    void onSuccess(List<Genre> movieList);

    void onError();
}
