package com.pereira.tiago.desafio.mobile.details;

import android.content.Context;

import com.pereira.tiago.desafio.mobile.databasemodels.SendDetails;

public interface Contract {

    interface DetailsModel{
        void getDetailsMovie(int movie_id);
        void getDetailsMovieDatabase(int movie_id);
        void getCastMovie(SendDetails sendDetails);
        void getCastMovieDatabase(SendDetails sendDetails);
    }

    interface DetailsPresenter {
        Context getContext();
        void setView(DetailsView view);
        void getDetailsMovie(int movie_id);
        void getCastMovie(SendDetails sendDetails);
        void setDetailsMovie(SendDetails sendDetails);
    }

    interface DetailsView {
        void showNoResults();
        void showResults(SendDetails sendDetails);
    }
}
