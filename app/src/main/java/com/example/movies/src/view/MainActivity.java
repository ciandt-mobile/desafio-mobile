package com.example.movies.src.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movies.R;
import com.example.movies.src.controller.MovieController;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private MovieController movieController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieController = new MovieController(MainActivity.this);

        Button btnUpcomingMovies = findViewById(R.id.btnUpcoming);
        btnUpcomingMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieController.showUpcomingVideos();
            }
        });

        Button btnPopularMovies = findViewById(R.id.btnPopular);
        btnPopularMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieController.showPopularVideos();
            }
        });

        Button btnMyFavouriteMovies = findViewById(R.id.btnMyFavourite);
        btnMyFavouriteMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieController.showFavouritesVideos();
            }
        });
    }
}
