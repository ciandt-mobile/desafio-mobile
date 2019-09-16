package com.rangeldor.movieapp.view.home;

import java.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rangeldor.movieapp.Utils;
import com.rangeldor.movieapp.model.Movie;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, -3);

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        String formattedDate = ano+"-"+mes+"-"+dia;

        Log.d(TAG, "getMovieByPopularity: " + formattedDate);

      //  String release_date_gte = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

        Call<Movie> movieCall = Utils.getApi().getMovieToPopularity ( "2019-05-12","1" );
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
