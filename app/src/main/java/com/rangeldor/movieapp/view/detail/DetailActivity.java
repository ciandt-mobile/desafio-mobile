package com.rangeldor.movieapp.view.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rangeldor.movieapp.R;
import com.rangeldor.movieapp.model.Movie;
import com.rangeldor.movieapp.view.home.HomeActivity;

import java.util.List;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_detail );
        ButterKnife.bind ( this );

        Intent intent = getIntent ();
        String movie_id = intent.getStringExtra ( HomeActivity.EXTRA_DETAIL_ID );
        DetailPresenter presenter = new DetailPresenter (this);
        presenter.getMovieById(movie_id);
    }

    @Override
    public void showLoading() {
        Log.d ( TAG , "showLoading: " );
    }

    @Override
    public void hideLoading() {
        Log.d ( TAG , "hideLoading: " );
    }

    @Override
    public void setResult(Movie.Result result) {
        Log.d ( TAG , "setResults: " + result.getTitle ());
    }

    @Override
    public void onErrorLoading(String message) {
        Log.d ( TAG , "onErrorLoading: " );
    }
}
