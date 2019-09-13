package com.rangeldor.movieapp.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rangeldor.movieapp.R;
import com.rangeldor.movieapp.Utils;
import com.rangeldor.movieapp.adapter.RecyclerViewHomeAdapter;
import com.rangeldor.movieapp.model.Movie;
import com.rangeldor.movieapp.view.detail.DetailActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView{

    private static final String TAG = "HomeActivity";
    public static final String EXTRA_DETAIL_ID = "MOVIE_ID";

    @BindView ( R.id.recyclerHome )
    RecyclerView recyclerViewHome;

    @BindView ( R.id.cardSearch )
    CardView cardSearch;

    HomePresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_home );
        ButterKnife.bind(this);

        presenter = new HomePresenter ( this );
        presenter.getMovieByPopularity ();


        ///teste

        cardSearch.setOnClickListener ( view -> {
            Log.d ( TAG , "onCreate: teste");
        } );
    }

    @Override
    public void showLoading() {
        findViewById ( R.id.shimmerHome ).setVisibility ( View.VISIBLE );
        Log.d ( TAG , "showLoading: " );
    }

    @Override
    public void hideLoading() {
        findViewById ( R.id.shimmerHome ).setVisibility ( View.GONE );
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
            Intent intent = new Intent ( HomeActivity.this, DetailActivity.class );
            intent.putExtra ( EXTRA_DETAIL_ID, String.valueOf ( results.get ( position ).getId () ) );
            startActivity ( intent );
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error : ", message);
        Log.d ( TAG , "onErrorLoading: " + message);
    }
}
