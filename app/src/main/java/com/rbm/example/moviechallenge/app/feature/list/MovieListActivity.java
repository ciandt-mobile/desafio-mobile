package com.rbm.example.moviechallenge.app.feature.list;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.data.data.MovieData;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private static final String TAG = MovieListActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<MovieData> movieDataList = new ArrayList<MovieData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        recyclerView = findViewById(R.id.rvContacts);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(movieDataList);
        recyclerView.setAdapter(adapter);

        MovieListViewModel viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        viewModel.loadMovies();

        viewModel.viewState.observe(this, movieListViewState -> {
            if (movieListViewState.isLoading()){
                Log.d(TAG, "Is loading");
            } else {
                Log.d(TAG, "Is not loading");
            }

            if (movieListViewState.getMovieList().size() > 0){
                int position = movieDataList.size();
                movieDataList.addAll(movieListViewState.getMovieList());
                adapter.notifyItemRangeInserted(position, movieListViewState.getMovieList().size());
            }
        });
    }

}
