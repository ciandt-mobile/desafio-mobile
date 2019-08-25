package com.svmdev.appmovies.view.details;

import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;
import com.svmdev.appmovies.R;
import com.svmdev.appmovies.data.model.Cast;
import com.svmdev.appmovies.data.model.MovieDetail;
import com.svmdev.appmovies.data.webservice.RetrofitConfig;
import com.svmdev.appmovies.data.webservice.URLs;
import com.svmdev.appmovies.data.webservice.results.error.GenreError;
import com.svmdev.appmovies.data.webservice.results.success.CastResult;
import com.svmdev.appmovies.help.ImageLoader;
import com.svmdev.appmovies.help.Variables;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {

    private final DetailActivity mActivity;

    public DetailPresenter(DetailActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void loadCast(int movieId) {
        loading(true);
        String callUrl = URLs.detailUrl + movieId + "/credits";

        Call<JsonElement> call = new RetrofitConfig().getAPI().getMovieDetails(
                callUrl,
                URLs.apiKey,
                URLs.language
        );

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                if (response.raw().code() == 200) {
                    CastResult castResult = gson.fromJson(response.body(), CastResult.class);
                    Variables.movieCast.addAll(castResult.cast);
                    mActivity.setListAdapter();
                } else {
                    try {
                        GenreError genreError = gson.fromJson(response.errorBody().string(), GenreError.class);
                        Toast.makeText(mActivity.context(), genreError.statusMessage, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                loading(false);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                loading(false);
                Toast.makeText(mActivity.context(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void loadMovieDetail(int movieId) {
        loading(true);

        String callUrl = URLs.detailUrl + movieId;

        Call<JsonElement> call = new RetrofitConfig().getAPI().getMovieDetails(
                callUrl,
                URLs.apiKey,
                URLs.language
        );

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                if (response.raw().code() == 200) {

                    MovieDetail movieDetail = gson.fromJson(response.body(), MovieDetail.class);

                    showDetails(movieDetail);
                    mActivity.setListAdapter();
                } else {
                    try {
                        GenreError genreError = gson.fromJson(response.errorBody().string(), GenreError.class);
                        Toast.makeText(mActivity.context(), genreError.statusMessage, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                loading(false);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                loading(false);
                Toast.makeText(mActivity.context(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loading(boolean isLoading) {
        mActivity.image().setEnabled(!isLoading);
        mActivity.title().setEnabled(!isLoading);
        mActivity.date().setEnabled(!isLoading);
        mActivity.duration().setEnabled(!isLoading);
        mActivity.genres().setEnabled(!isLoading);
        mActivity.overview().setEnabled(!isLoading);

        mActivity.progress().setIndeterminate(isLoading);
        if (isLoading) {
            mActivity.progress().setVisibility(View.VISIBLE);
        } else {
            mActivity.progress().setVisibility(View.GONE);
        }
    }

    private void showDetails(MovieDetail show) {

        String movieRuntime = show.getRuntime() + "min";

        mActivity.title().setText(show.getTitle());
        mActivity.date().setText(show.getRealeseDateYear());
        mActivity.duration().setText(movieRuntime);
        mActivity.overview().setText(show.getOverview());
        if (show.getOverview().isEmpty()){
            mActivity.overview().setText(R.string.no_resume);
        }
        mActivity.genres().setText(show.getListedGenres());

        if(!show.getBackdropPathFormated().isEmpty()) {
            Picasso.with(mActivity.context()).load(show.getBackdropPathFormated())
                    .placeholder(R.drawable.download)
                    .error(R.drawable.no_image)
                    .into(mActivity.image(), new com.squareup.picasso.Callback() {

                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }


                    });
        } else {
            mActivity.image().setImageResource(R.drawable.no_image);
        }

    }

}