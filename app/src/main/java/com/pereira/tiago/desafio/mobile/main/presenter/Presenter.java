package com.pereira.tiago.desafio.mobile.main.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.pereira.tiago.desafio.mobile.databasemodels.Movie;
import com.pereira.tiago.desafio.mobile.main.Contract;
import com.pereira.tiago.desafio.mobile.main.model.Model;
import com.pereira.tiago.desafio.mobile.utils.ConnectivityInfo;

import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.pereira.tiago.desafio.mobile.base.Config.INTERNET;
import static com.pereira.tiago.desafio.mobile.base.Config.READ_EXTERNAL;
import static com.pereira.tiago.desafio.mobile.base.Config.WRITE_EXTERNAL;

public class Presenter implements Contract.MainPresenter {

    private Contract.MainModel model;
    private Contract.MainView view;

    public Presenter(){
        model = new Model(this);
    }

    @Override
    public Context getContext() {
        return (Context)view;
    }

    @Override
    public void getPermissions() {
        boolean flagExternal = false;
        boolean flagRead = false;
        boolean flagInternet = false;

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)view, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL);
        } else {
            flagExternal = true;
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)view, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL);
        } else {
            flagRead = true;
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)view, new String[]{Manifest.permission.INTERNET}, INTERNET);
        } else {
            flagInternet = true;
        }

        if (flagExternal && flagRead && flagInternet){
            getListMoviesPopular();
        } else {
            view.showToast("Você precisa dar todas as permissôes necessárias");
        }
    }

    @Override
    public void setView(Contract.MainView view) {
        this.view = view;
    }

    @Override
    public void getListMoviesPopular() {
        ConnectivityInfo.init(getContext());
        if (ConnectivityInfo.isConnected() && (ConnectivityInfo.is3G() || ConnectivityInfo.isWifi())) {
            model.getMoviesPopularNetwork();
        } else {
            model.getMoviesPopularDatabase();
        }
    }

    @Override
    public void setMovieList(List<Movie> movieList) {
        if (!movieList.isEmpty()) {
            view.setPopulateRecycler(movieList);
        } else {
            view.showNoResults();
        }
    }
}
