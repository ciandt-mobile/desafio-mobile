package com.rbm.example.moviechallenge.data.repository.movies.remote;

import android.util.Log;

import com.rbm.example.moviechallenge.data.api.Api;
import com.rbm.example.moviechallenge.data.api.ApiClient;
import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.data.data.MovieListData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiMoviesDataSource implements RemoteMoviesDataSource {

    public final static String TAG = ApiMoviesDataSource.class.getSimpleName();

    @Override
    public List<MovieData> getMovieList() {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Api api = ApiClient.getClient().create(Api.class);
                String api_key = "75c9554b8dfcd6303c5be0744fbf43e5";

                Call<MovieListData> call = api.getMovieList(api_key);
                call.enqueue(new Callback<MovieListData>() {
                    @Override
                    public void onResponse(Call<MovieListData> call, Response<MovieListData> response) {
                        Log.d(TAG, "**** Request response ****");
                        if (response.body() != null) {
                            MovieListData movieListData = response.body();
                            Log.d(TAG, "SEgue o jogo que pegou a lista");
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieListData> call, Throwable t) {
                        Log.d(TAG, "**** Request fail ****");
                    }
                });
            }
        }).start();*/

        return null;
    }

    @Override
    public MovieData getMovie(int movieId) {
        return null;
    }
}
