package br.com.ciandt.application.fellini.service.callbacks;

import java.util.List;

import br.com.ciandt.application.fellini.domain.movie.Movie;
import br.com.ciandt.application.fellini.domain.movie.MovieResponse;
import retrofit2.Call;

public interface OnGettingMoviesCallback {

    void onSuccess(int page, List<Movie> movieList);

    void onError();
}
