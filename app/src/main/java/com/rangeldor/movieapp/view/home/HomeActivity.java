package com.rangeldor.movieapp.view.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import java.text.SimpleDateFormat;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rangeldor.movieapp.R;
import com.rangeldor.movieapp.Utils;
import com.rangeldor.movieapp.adapter.RecyclerViewHomeAdapter;
import com.rangeldor.movieapp.model.Movie;
import com.rangeldor.movieapp.view.detail.DetailActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView, BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";
    public static final String EXTRA_DETAIL_ID = "MOVIE_ID";

    List<Movie.Result> results;

    @BindString ( R.string.language )
    String LANGUAGE;

    @BindView ( R.id.recyclerHome )
    RecyclerView recyclerViewHome;

    @BindView(R.id.bottom_nav_home)
    BottomNavigationView navigationView;

    @BindView(R.id.titleType)
    TextView titleType;

    HomePresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_home );
        ButterKnife.bind(this);

        presenter = new HomePresenter ( this );
        presenter.getMovieByPopularity (LANGUAGE);

        navigationView.setOnNavigationItemSelectedListener(this);

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

        this.results = results;

        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter ( results, this );
        recyclerViewHome.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setNestedScrollingEnabled ( true );
        homeAdapter.notifyDataSetChanged ();

        homeAdapter.setOnItemClickListener ( (view , position , homeThumb) -> {
            Intent intent = new Intent ( HomeActivity.this, DetailActivity.class );
            intent.putExtra ( EXTRA_DETAIL_ID, String.valueOf ( results.get ( position ).getId () ) );

            Pair<View, String> pair = Pair.create ( homeThumb, ViewCompat.getTransitionName ( homeThumb ) );
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation ( HomeActivity.this, pair );

            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ) {
                startActivity ( intent , optionsCompat.toBundle ( ) );
            } else {
                startActivity ( intent );
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error : ", message);
        Log.d ( TAG , "onErrorLoading: " + message);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.menu_popularity: {

                titleType.setText(getString(R.string.movie_title_type_popularity));

                RecyclerViewHomeAdapter homeAdapterNew = new RecyclerViewHomeAdapter ( this.results, this );
                recyclerViewHome.setAdapter(homeAdapterNew);
                GridLayoutManager layoutManagerNew = new GridLayoutManager(this, 2,
                        GridLayoutManager.VERTICAL, false);
                recyclerViewHome.setLayoutManager(layoutManagerNew);
                recyclerViewHome.setNestedScrollingEnabled ( true );
                homeAdapterNew.notifyDataSetChanged ();

                break;
            }

            case R.id.menu_debut: {

                titleType.setText(getString(R.string.movie_title_type_debut));

                List<Movie.Result> newListResults = new ArrayList<>();

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date2 = null;

                Calendar calendar = Calendar.getInstance();

                // Retirar para retornar a data atual e obter somente os filmes Em breve
                calendar.add(Calendar.MONTH, -3);

                //Precisa colocar a paginação

                for (Movie.Result result : this.results){

                    try {
                        date2 = format.parse(result.getReleaseDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if ( date2 != null ) {
                        if (date2.after(calendar.getTime ())){
                            newListResults.add(result);
                        }
                    }
                }

                RecyclerViewHomeAdapter homeAdapterNew = new RecyclerViewHomeAdapter ( newListResults, this );
                recyclerViewHome.setAdapter(homeAdapterNew);
                GridLayoutManager layoutManagerNew = new GridLayoutManager(this, 2,
                        GridLayoutManager.VERTICAL, false);
                recyclerViewHome.setLayoutManager(layoutManagerNew);
                recyclerViewHome.setNestedScrollingEnabled ( true );
                homeAdapterNew.notifyDataSetChanged ();

                break;
            }

        }

        return true;
    }
}
