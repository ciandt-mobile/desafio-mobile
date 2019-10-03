package com.pereira.tiago.desafio.mobile.details.presenter;

import android.content.Context;

import com.pereira.tiago.desafio.mobile.databasemodels.SendDetails;
import com.pereira.tiago.desafio.mobile.details.Contract;
import com.pereira.tiago.desafio.mobile.details.model.DetailsModel;
import com.pereira.tiago.desafio.mobile.utils.ConnectivityInfo;

public class DetailsPresenter implements Contract.DetailsPresenter {

    Contract.DetailsModel model;
    Contract.DetailsView view;

    public DetailsPresenter() {
        model = new DetailsModel(this);
    }

    @Override
    public Context getContext() {
        return (Context)view;
    }

    @Override
    public void setView(Contract.DetailsView view) {
        this.view = view;
    }

    @Override
    public void getDetailsMovie(int movie_id) {
        ConnectivityInfo.init(getContext());
        if (ConnectivityInfo.isConnected() && (ConnectivityInfo.is3G() || ConnectivityInfo.isWifi())) {
            model.getDetailsMovie(movie_id);
        } else {
            model.getDetailsMovieDatabase(movie_id);
        }
    }

    @Override
    public void getCastMovie(SendDetails sendDetails) {

        if (sendDetails == null) view.showNoResults();

        ConnectivityInfo.init(getContext());
        if (ConnectivityInfo.isConnected() && (ConnectivityInfo.is3G() || ConnectivityInfo.isWifi())) {
            model.getCastMovie(sendDetails);
        } else {
            model.getCastMovieDatabase(sendDetails);
        }
    }

    @Override
    public void setDetailsMovie(SendDetails sendDetails) {
        if (sendDetails != null){
            view.showResults(sendDetails);
        } else {
            view.showNoResults();
        }
    }
}
