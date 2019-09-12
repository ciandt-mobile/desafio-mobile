package com.rangeldor.movieapp.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rangeldor.movieapp.R;
import com.rangeldor.movieapp.adapter.RecyclerViewHomeAdapter;
import com.rangeldor.movieapp.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView{

    private static final String TAG = "HomeActivity";

    @BindView ( R.id.recyclerHome )
    RecyclerView recyclerViewHome;
    
    HomePresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_home );
        ButterKnife.bind(this);
        
        presenter = new HomePresenter ( this );
        presenter.getMovieToPopularity ();
    }

    @Override
    public void showLoading() {
      //  findViewById ( R.id.shimmerHome ).setVisibility ( View.VISIBLE );
        Log.d ( TAG , "showLoading: " );
    }

    @Override
    public void hideLoading() {
      //  findViewById ( R.id.shimmerHome ).setVisibility ( View.GONE );
        Log.d ( TAG , "hideLoading: " );
    }

    @Override
    public void setResults(List<Movie.Result> results) {

        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter ( results, this );
        recyclerViewHome.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setNestedScrollingEnabled ( true );
        homeAdapter.notifyDataSetChanged ();

        homeAdapter.setOnItemClickListener ( (view , position) -> {

            Log.d ( TAG , "setResults: Clicado!" + results.get(position).getId());
        });
        for(Movie.Result result : results){
            Log.d ( TAG , "setResults: " + result.getTitle () );
        }
    }

    @Override
    public void onErrorLoading(String message) {
        Log.d ( TAG , "onErrorLoading: " + message);
    }
}
