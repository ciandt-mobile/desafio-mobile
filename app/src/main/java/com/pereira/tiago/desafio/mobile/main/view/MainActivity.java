package com.pereira.tiago.desafio.mobile.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.pereira.tiago.desafio.mobile.R;
import com.pereira.tiago.desafio.mobile.base.Config;
import com.pereira.tiago.desafio.mobile.base.PaginationListener;
import com.pereira.tiago.desafio.mobile.databasemodels.Movie;
import com.pereira.tiago.desafio.mobile.main.Contract;
import com.pereira.tiago.desafio.mobile.main.presenter.Presenter;
import com.pereira.tiago.desafio.mobile.utils.Shared;
import com.pereira.tiago.desafio.mobile.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import me.rishabhkhanna.customtogglebutton.CustomToggleButton;

import static com.pereira.tiago.desafio.mobile.base.PaginationListener.PAGE_START;

public class MainActivity extends AppCompatActivity implements Contract.MainView,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainActivity";

    Toolbar toolbar;
    ImageView imgNoResults;
    View viewDivider, viewDivider2;
    TextView txtNoResults;
    CustomToggleButton buttonPopular, buttonUpcoming;
    RecyclerView rcvMovies;
    ProgressBar pbLoading;
    SwipeRefreshLayout swipeRefresh;
    private static  Contract.MainPresenter presenter;
    MainAdapter adapter;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Shared.setPrefStatus(getApplicationContext(), true);

        if (presenter == null){
            presenter = new Presenter();
        }
        presenter.setView(this);

        init();
    }

    private void init(){
        toolbar = findViewById(R.id.toolbar);
        imgNoResults = findViewById(R.id.imgNoResults);
        viewDivider = findViewById(R.id.viewDivider);
        txtNoResults = findViewById(R.id.txtNoResults);
        rcvMovies = findViewById(R.id.rcvMovies);
        buttonPopular = findViewById(R.id.buttonPopular);
        buttonUpcoming = findViewById(R.id.buttonUpcoming);
        viewDivider2 = findViewById(R.id.viewDivider2);
        pbLoading = findViewById(R.id.pbLoading);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        swipeRefresh.setOnRefreshListener(this);
        rcvMovies.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvMovies.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(new ArrayList<Movie>());
        rcvMovies.setAdapter(adapter);

        rcvMovies.addOnScrollListener(new PaginationListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                //if (option.equals(Config.POPULAR)){
                    presenter.getListMoviesPopular(currentPage);
//                } else if (option.equals(Config.UPCOMING)){
//                    presenter.getListMoviesUpcoming();
//                }
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        buttonPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.changeOption(Config.POPULAR, currentPage);
            }
        });

        buttonUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.changeOption(Config.UPCOMING, currentPage);
            }
        });

        pbLoading.setVisibility(View.VISIBLE);
        swipeRefresh.setVisibility(View.GONE);
        rcvMovies.setVisibility(View.GONE);

        presenter.changeOption(Config.POPULAR, currentPage);
    }

    @Override
    public void showNoResults() {
        swipeRefresh.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        rcvMovies.setVisibility(View.GONE);
        buttonPopular.setVisibility(View.GONE);
        buttonUpcoming.setVisibility(View.GONE);
        viewDivider2.setVisibility(View.GONE);
        imgNoResults.setVisibility(View.VISIBLE);
        viewDivider.setVisibility(View.VISIBLE);
        txtNoResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void showButtonPopular() {
        buttonPopular.setChecked(true);
        buttonUpcoming.setChecked(false);
    }

    @Override
    public void showButtonUpcoming() {
        buttonPopular.setChecked(false);
        buttonUpcoming.setChecked(true);
    }

    @Override
    public void setPopulateRecycler(final List<Movie> movies, final String option) {

        swipeRefresh.setVisibility(View.VISIBLE);
        rcvMovies.setVisibility(View.VISIBLE);
        buttonPopular.setVisibility(View.VISIBLE);
        buttonUpcoming.setVisibility(View.VISIBLE);
        viewDivider2.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
        imgNoResults.setVisibility(View.GONE);
        viewDivider.setVisibility(View.GONE);
        txtNoResults.setVisibility(View.GONE);

        final ArrayList<Movie> items = new ArrayList<>();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    itemCount++;
                    Movie movie = new Movie();
                    movie.setTitle(movies.get(i).getTitle());
                    movie.setRelease_date(movies.get(i).getRelease_date());
                    movie.setBackdrop_path(movies.get(i).getBackdrop_path());
                    movie.setPoster_path(movies.get(i).getPoster_path());
                    items.add(movie);
                }
                /**
                 * manage progress view
                 */
                if (currentPage != PAGE_START) adapter.removeLoading();
                adapter.addItems(items);
                swipeRefresh.setRefreshing(false);

                // check weather is last page or not
                if (currentPage < totalPage) {
                    adapter.addLoading();
                } else {
                    isLastPage = true;
                }
                isLoading = false;
            }
        }, 1500);
    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        presenter.getListMoviesPopular(currentPage);
    }
}
