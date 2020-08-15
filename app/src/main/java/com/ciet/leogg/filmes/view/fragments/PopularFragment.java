package com.ciet.leogg.filmes.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.presenter.MoviesContract;
import com.ciet.leogg.filmes.presenter.TabPresenter;
import com.ciet.leogg.filmes.repository.MainRepository;
import com.ciet.leogg.filmes.view.recyclerviews.MoviesRecyclerView;

import java.util.List;


public class PopularFragment extends Fragment implements MoviesContract.ListView {
    private MoviesContract.TabInteraction tabInteraction;
    private MoviesRecyclerView moviesRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public PopularFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate","start");
        tabInteraction = new ViewModelProvider(this).get(TabPresenter.class);
        tabInteraction.setPopularView(this);
        tabInteraction.loadMovies();
        Log.d("onCreate","end");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView","start");
        swipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_popular, container, false);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainRepository.getInstance().more();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        moviesRecyclerView = swipeRefreshLayout.findViewById(R.id.popular_list);
        moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        && !recyclerView.canScrollVertically(1)){
                    recyclerView.smoothScrollBy(0,-1);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING
                        && !recyclerView.canScrollVertically(1)){
                    swipeRefreshLayout.setRefreshing(true);
                    MainRepository.getInstance().less();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        Log.d("onCreateView","end");
        return swipeRefreshLayout;
    }

    @Override
    public void showMovies(List<Movie> movies) {
        Log.d("showMovies","start");
        moviesRecyclerView.getMoviesAdapter().submitList(movies);
        swipeRefreshLayout.setRefreshing(false);
        Log.d("showMovies","end");
    }
}

