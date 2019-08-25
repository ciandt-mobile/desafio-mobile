package com.svmdev.appmovies.view.popular;

import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.svmdev.appmovies.data.model.Movie;
import com.svmdev.appmovies.data.webservice.URLs;
import com.svmdev.appmovies.data.webservice.results.error.GenreError;
import com.svmdev.appmovies.data.webservice.RetrofitConfig;
import com.svmdev.appmovies.data.webservice.results.success.MovieResult;
import com.svmdev.appmovies.data.webservice.send.MovieSend;
import com.svmdev.appmovies.help.Variables;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PopularPresenter {

    private final PopularFragment mFrag;

    public PopularPresenter(PopularFragment mFrag) {
        this.mFrag = mFrag;
    }

    public void loadList(int page) {

        loading(true);

        MovieSend popularSend = new MovieSend();
        popularSend.apiKey = URLs.apiKey;
        popularSend.language = URLs.language;
        popularSend.page = page;


        Call<JsonElement> call = new RetrofitConfig().getAPI().getPopular(
                popularSend.apiKey,
                popularSend.language,
                popularSend.page
        );

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                loading(false);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                if (response.raw().code() == 200) {
                    MovieResult movieResult = gson.fromJson(response.body(), MovieResult.class);
                    setMovieList(movieResult.results);

                    mFrag.adapter().notifyDataSetChanged();

                    if (Variables.popularPages.isEmpty()) {
                        setPopularPages(movieResult.totalPages);
                        mFrag.setSpinnerAdpater();
                    }

                } else {
                    try {
                        GenreError genreError = gson.fromJson(response.errorBody().string(), GenreError.class);
                        Toast.makeText(mFrag.context(), genreError.statusMessage, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                loading(false);
                Toast.makeText(mFrag.context(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loading(boolean isLoading) {
        mFrag.listView().setEnabled(!isLoading);
        mFrag.pageSpinner().setEnabled(!isLoading);
        mFrag.progress().setIndeterminate(isLoading);
        if (isLoading) {
            mFrag.progress().setVisibility(View.VISIBLE);
        } else {
            mFrag.progress().setVisibility(View.GONE);
        }
    }


    private void setMovieList(List<Movie> movies) {
        Variables.popularList.clear();
        Variables.popularList.addAll(movies);
    }

    private void setPopularPages(int total) {
        Variables.popularPages.clear();
        for (int index = 1; index <= total; index++) {
            Variables.popularPages.add(index);
        }
    }

}
