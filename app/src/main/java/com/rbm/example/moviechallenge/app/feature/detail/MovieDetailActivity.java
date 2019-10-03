package com.rbm.example.moviechallenge.app.feature.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.app.feature.detail.ui.moviedetail.MovieDetailFragment;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MovieDetailFragment.newInstance())
                    .commitNow();
        }
    }
}
