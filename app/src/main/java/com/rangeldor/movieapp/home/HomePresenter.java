package com.rangeldor.movieapp.home;

import android.support.annotation.NonNull;

import com.rangeldor.movieapp.Utils;
import com.rangeldor.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView view;

    public HomePresenter(HomeView view){
        this.view = view;
    }

    void getMovieToPopularity(){

        view.showLoading ();

        Call<Movie> movieCall = Utils.getApi().getMovieToPopularity ();
        movieCall.enqueue(new Callback<Movie> () {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.setResults ( response.body().getResults () );

                    //  Log.d ( TAG , "onResponse: " +  results);

                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
