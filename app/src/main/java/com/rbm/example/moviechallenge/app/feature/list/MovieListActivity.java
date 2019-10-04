package com.rbm.example.moviechallenge.app.feature.list;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
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
        recyclerView = findViewById(R.id.rvMovies);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(movieDataList);
        recyclerView.setAdapter(adapter);

        MovieListViewModel viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        viewModel.loadMovies();

        viewModel.viewState.getIsLoading().observe(this, aBoolean -> {
            if (aBoolean){
                Log.d(TAG, "Is loading");
            } else {
                Log.d(TAG, "Is not loading");
            }
        });

        viewModel.viewState.getMovieList().observe(this, movieListData -> {
            if (movieListData.getMovieList().size() > 0){
                int position = movieDataList.size();
                movieDataList.addAll(movieListData.getMovieList());
                adapter.notifyItemRangeInserted(position, movieListData.getMovieList().size());
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                final int visibleThreshold = 6;

                GridLayoutManager layoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
                int lastItem  = 0;
                int currentTotalCount = 0;

                if (layoutManager != null) {
                    lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    currentTotalCount = layoutManager.getItemCount();
                }

                if(currentTotalCount <= lastItem + visibleThreshold){
                    viewModel.loadNextPage();
                }
            }
        });
    }
}
