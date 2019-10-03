package com.rbm.example.moviechallenge.app;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.app.feature.list.MovieListViewModel;
import com.rbm.example.moviechallenge.app.feature.list.MovieListViewState;
import com.rbm.example.moviechallenge.data.repository.movies.remote.ApiMoviesDataSource;

public class ScrollingActivity extends AppCompatActivity {

    private static final String TAG = ScrollingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MovieListViewModel viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        viewModel.loadMovies();

        viewModel.viewState.observe(this, movieListViewState -> {
            if (movieListViewState.isLoading()){
                Log.d(TAG, "Is loading");
            } else {
                Log.d(TAG, "Is not loading");
            }

            if (movieListViewState.getMovieList().size() > 0){
                Log.d(TAG, "Got a movie list");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiMoviesDataSource api = new ApiMoviesDataSource();
                api.getMovieList();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
