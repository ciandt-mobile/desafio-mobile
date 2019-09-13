package com.rangeldor.movieapp.view.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.rangeldor.movieapp.Utils;
import com.rangeldor.movieapp.model.Movie;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class HomePresenter {

    private HomeView view;

    public HomePresenter(HomeView view){
        this.view = view;
    }

    void getMovieByPopularity(){

        view.showLoading ();

        //Log.d ( TAG , "getMovieByPopularity: " + String.valueOf ( Calendar.getInstance().getTime() ));

        Call<Movie> movieCall = Utils.getApi().getMovieToPopularity ( "2019-06-13" );
        movieCall.enqueue(new Callback<Movie> () {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    view.setResults ( response.body().getResults () );
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
