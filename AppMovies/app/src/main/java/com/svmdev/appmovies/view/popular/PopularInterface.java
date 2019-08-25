package com.svmdev.appmovies.view.popular;

import android.content.Context;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.svmdev.appmovies.view.adapter.MovieAdapter;

public interface PopularInterface {

    Context context();
    ListView listView();
    MovieAdapter adapter();
    Spinner pageSpinner();
    ProgressBar progress();

}