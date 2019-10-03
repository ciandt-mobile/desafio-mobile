package com.pereira.tiago.desafio.mobile.details.model;

import android.util.Log;

import com.pereira.tiago.desafio.mobile.base.BaseRetrofit;
import com.pereira.tiago.desafio.mobile.base.BaseService;
import com.pereira.tiago.desafio.mobile.base.Config;
import com.pereira.tiago.desafio.mobile.databasemodels.CastMovie;
import com.pereira.tiago.desafio.mobile.databasemodels.Details;
import com.pereira.tiago.desafio.mobile.databasemodels.GenreMovie;
import com.pereira.tiago.desafio.mobile.databasemodels.SendDetails;
import com.pereira.tiago.desafio.mobile.details.Contract;
import com.pereira.tiago.desafio.mobile.reponse.ApiResponseCredits;
import com.pereira.tiago.desafio.mobile.reponse.ApiResponseDetails;
import com.pereira.tiago.desafio.mobile.reponse.Cast;
import com.pereira.tiago.desafio.mobile.reponse.Genre;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsModel implements Contract.DetailsModel {

    Contract.DetailsPresenter presenter;

    public DetailsModel(Contract.DetailsPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void getDetailsMovie(int movie_id) {

        final SendDetails sendDetails = new SendDetails();

//        try {
//            SingletonMovie.getInstance().deleteMovies();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        BaseService baseService = BaseRetrofit.getBaseRetrofit(presenter.getContext())
                .create(BaseService.class);
        Call<ApiResponseDetails> call = baseService.getMovieDetails(String.valueOf(movie_id), Config.KEY_API, Config.LANGUAGE);
        call.enqueue(new Callback<ApiResponseDetails>() {
            @Override
            public void onResponse(Call<ApiResponseDetails> call, Response<ApiResponseDetails> response) {
                if (response.code() == 200 && response.body() != null){

                    Details details = new Details();
                    details.setId(response.body().getId());
                    details.setAdult(response.body().isAdult());
                    details.setTitle(response.body().getTitle());
                    details.setBackdrop_path(response.body().getBackdropPath());
                    details.setRelease_date(response.body().getReleaseDate());
                    details.setOverview(response.body().getOverview());
                    details.setPoster_path(response.body().getPosterPath());
                    details.setRuntime(response.body().getRuntime());

                    List<GenreMovie> genreMovieList = new ArrayList<>();
                    for (Genre genre: response.body().getGenres()){
                        GenreMovie genreMovie = new GenreMovie();
                        genreMovie.setMovie_id(response.body().getId());
                        genreMovie.setGenre_id(genre.getId());
                        genreMovie.setName(genre.getName());

                        genreMovieList.add(genreMovie);
                    }

                    sendDetails.setDetails(details);
                    sendDetails.setGenreMovieList(genreMovieList);

                    presenter.getCastMovie(sendDetails);

                }
            }

            @Override
            public void onFailure(Call<ApiResponseDetails> call, Throwable t) {
                Log.d("TAG", t.getMessage());

                presenter.getCastMovie(sendDetails);
            }
        });

    }

    @Override
    public void getDetailsMovieDatabase(int movie_id) {

    }

    @Override
    public void getCastMovie(final SendDetails sendDetails) {

        BaseService baseService = BaseRetrofit.getBaseRetrofit(presenter.getContext())
                .create(BaseService.class);
        Call<ApiResponseCredits> call = baseService.getCredits(String.valueOf(sendDetails.getDetails().getId()), Config.KEY_API);
        call.enqueue(new Callback<ApiResponseCredits>() {
            @Override
            public void onResponse(Call<ApiResponseCredits> call, Response<ApiResponseCredits> response) {
                if (response.code() == 200 && response.body() != null){

                    List<CastMovie> castMovieList = new ArrayList<>();
                    for (Cast cast: response.body().getCast()){
                        CastMovie castMovie = new CastMovie();
                        castMovie.setCastId(cast.getCastId());
                        castMovie.setCharacter(cast.getCharacter());
                        castMovie.setName(cast.getName());
                        castMovie.setProfilePath(cast.getProfilePath());

                        castMovieList.add(castMovie);
                    }

                    sendDetails.setCastMovieList(castMovieList);

                    presenter.setDetailsMovie(sendDetails);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseCredits> call, Throwable t) {
                Log.d("TAG", t.getMessage());

                presenter.setDetailsMovie(sendDetails);
            }
        });
    }

    @Override
    public void getCastMovieDatabase(SendDetails sendDetails) {

    }
}
