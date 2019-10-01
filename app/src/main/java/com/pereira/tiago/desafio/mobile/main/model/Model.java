package com.pereira.tiago.desafio.mobile.main.model;

import android.util.Log;

import com.pereira.tiago.desafio.mobile.base.BaseRetrofit;
import com.pereira.tiago.desafio.mobile.base.BaseService;
import com.pereira.tiago.desafio.mobile.base.Config;
import com.pereira.tiago.desafio.mobile.databasemodels.Movie;
import com.pereira.tiago.desafio.mobile.main.Contract;
import com.pereira.tiago.desafio.mobile.reponse.ApiResponse;
import com.pereira.tiago.desafio.mobile.reponse.Result;
import com.pereira.tiago.desafio.mobile.singletons.SingletonMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.MainModel {

    private Contract.MainPresenter presenter;

    public Model(Contract.MainPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void getMoviesPopularNetwork() {

        final List<Movie> movieList = new ArrayList<>();

        BaseService baseService = BaseRetrofit.getBaseRetrofit(presenter.getContext())
                .create(BaseService.class);
        Call<ApiResponse> call = baseService.getPopularMovies(Config.KEY_API, Config.LANGUAGE, Config.PAGE);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200 && response.body() != null){
                    for (Result result: response.body().getResults()){
                        Movie movie = new Movie();
                        movie.setId(result.getId());
                        movie.setTitle(result.getTitle());
                        movie.setRelease_date(result.getReleaseDate());
                        movie.setBackdrop_path(result.getBackdropPath());
                        movie.setPoster_path(result.getPosterPath());

                        try {
                            if (SingletonMovie.getInstance().saveMovie(movie)){
                                movieList.add(movie);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    presenter.setMovieList(movieList);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("TAG", t.getMessage());

                presenter.setMovieList(movieList);
            }
        });
    }

    @Override
    public void getMoviesPopularDatabase() {

    }
}
