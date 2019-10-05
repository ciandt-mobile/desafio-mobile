package com.rbm.example.moviechallenge.app.feature.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.app.feature.detail.MovieDetailActivity;
import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.data.data.MovieListData;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private static final String TAG = MovieListActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<MovieData> movieDataList = new ArrayList<MovieData>();
    private final static int GRID_RECYCLER_VIEW_SPAN_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        recyclerView = findViewById(R.id.rvMovies);

        MovieListViewModel viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.loadMovies();
        viewModel.viewState.getIsLoading().observe(this, this::onLoadingUpdate);
        viewModel.viewState.getMovieList().observe(this, this::onMovieListUpdate);

        layoutManager = new GridLayoutManager(this, GRID_RECYCLER_VIEW_SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieListAdapter(movieDataList, this::callDetailedActivity);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                final int visibleThreshold = 6;

                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastItem = 0;
                int currentTotalCount = 0;

                if (layoutManager != null) {
                    lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    currentTotalCount = layoutManager.getItemCount();
                }

                if (currentTotalCount <= lastItem + visibleThreshold) {
                    viewModel.loadNextPage();
                }
            }
        });
    }

    private void callDetailedActivity(MovieData movieData) {
        Intent i = new Intent(this, MovieDetailActivity.class);
        i.putExtra("id", movieData.getId());
        i.putExtra("title", movieData.getTitle());
        i.putExtra("release_date", movieData.getRelease_date());
        startActivity(i);
    }

    private void onLoadingUpdate(boolean isLoading) {
        if (isLoading) {
            Log.d(TAG, "Is loading");
        } else {
            Log.d(TAG, "Is not loading");
        }
    }

    private void onMovieListUpdate(MovieListData movieListData) {
        if (movieListData.getMovieList().size() > 0) {
            int position = movieDataList.size();
            movieDataList.addAll(movieListData.getMovieList());
            adapter.notifyItemRangeInserted(position, movieListData.getMovieList().size());
        }
    }
}
