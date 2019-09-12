package com.rangeldor.movieapp;

import android.app.AlertDialog;
import android.content.Context;

import com.rangeldor.movieapp.api.MovieApi;
import com.rangeldor.movieapp.api.MovieClient;


public class Utils {

    public static MovieApi getApi() {
        return MovieClient.getFoodClient().create(MovieApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}
