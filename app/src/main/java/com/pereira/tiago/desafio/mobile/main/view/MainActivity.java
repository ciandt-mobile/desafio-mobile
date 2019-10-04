package com.pereira.tiago.desafio.mobile.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pereira.tiago.desafio.mobile.R;
import com.pereira.tiago.desafio.mobile.base.Config;
import com.pereira.tiago.desafio.mobile.base.PaginationListener;
import com.pereira.tiago.desafio.mobile.databasemodels.Movie;
import com.pereira.tiago.desafio.mobile.main.Contract;
import com.pereira.tiago.desafio.mobile.main.presenter.Presenter;
import com.pereira.tiago.desafio.mobile.utils.Shared;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import me.rishabhkhanna.customtogglebutton.CustomToggleButton;

import static com.pereira.tiago.desafio.mobile.base.Config.POPULAR;
import static com.pereira.tiago.desafio.mobile.base.Config.UPCOMING;
import static com.pereira.tiago.desafio.mobile.base.PaginationListener.PAGE_START;

public class MainActivity extends AppCompatActivity implements Contract.MainView,
        SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    ImageView imgNoResults;
    View viewDivider;
    TextView txtNoResults;
    LinearLayout llType;
    CustomToggleButton buttonPopular, buttonUpcoming;
    RecyclerView rcvMovies;
    ProgressBar pbLoading;
    SwipeRefreshLayout swipeRefresh;
    private static Contract.MainPresenter presenter;
    MainAdapter adapter;
    private int currentPage = PAGE_START;
    private String opSearch = Config.POPULAR;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }

        return true;
    }

    private void init(){
        toolbar = findViewById(R.id.toolbar);
        imgNoResults = findViewById(R.id.imgNoResults);
        viewDivider = findViewById(R.id.viewDivider);
        txtNoResults = findViewById(R.id.txtNoResults);
        rcvMovies = findViewById(R.id.rcvMovies);
        llType = findViewById(R.id.llType);
        buttonPopular = findViewById(R.id.buttonPopular);
        buttonUpcoming = findViewById(R.id.buttonUpcoming);
        pbLoading = findViewById(R.id.pbLoading);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setSubtitle(getResources().getString(R.string.app_sub_title_main));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        swipeRefresh.setOnRefreshListener(this);
        rcvMovies.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rcvMovies.setLayoutManager(gridLayoutManager);
        adapter = new MainAdapter(new ArrayList<Movie>(), MainActivity.this);
        rcvMovies.setAdapter(adapter);

        rcvMovies.addOnScrollListener(new PaginationListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                if (opSearch.equals(POPULAR)) {
                    isLoading = true;
                    currentPage++;
                    presenter.changeOption(opSearch, currentPage);
                } else {
                    isLoading = false;
                }
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
                pbLoading.setVisibility(View.VISIBLE);
                currentPage = PAGE_START;
                adapter.clear();
                opSearch = Config.POPULAR;
                presenter.changeOption(opSearch, currentPage);
            }
        });

        buttonUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbLoading.setVisibility(View.VISIBLE);
                currentPage = PAGE_START;
                adapter.clear();
                opSearch = UPCOMING;
                presenter.changeOption(opSearch, currentPage);
            }
        });

        pbLoading.setVisibility(View.VISIBLE);
        swipeRefresh.setVisibility(View.GONE);
        rcvMovies.setVisibility(View.GONE);

        presenter.changeOption(opSearch, currentPage);
    }

    @Override
    public void showNoResults() {
        swipeRefresh.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        rcvMovies.setVisibility(View.GONE);
        llType.setVisibility(View.GONE);
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
        llType.setVisibility(View.VISIBLE);
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
                    movie.setId(movies.get(i).getId());
                    movie.setTitle(movies.get(i).getTitle());
                    movie.setRelease_date(movies.get(i).getRelease_date());
                    movie.setBackdrop_path(movies.get(i).getBackdrop_path());
                    movie.setPoster_path(movies.get(i).getPoster_path());
                    items.add(movie);
                }

                if (currentPage != PAGE_START) adapter.removeLoading();
                adapter.addItems(items);
                swipeRefresh.setRefreshing(false);

                // check weather is last page or not
                if (option.equals(POPULAR)) {
                    if (currentPage < totalPage) {
                        adapter.addLoading(true);
                    } else {
                        isLastPage = true;
                    }
                } else {
                    adapter.addLoading(false);
                }
                isLoading = false;

                opSearch = option;
            }
        }, 1500);

        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        presenter.changeOption(opSearch, currentPage);
    }
}
