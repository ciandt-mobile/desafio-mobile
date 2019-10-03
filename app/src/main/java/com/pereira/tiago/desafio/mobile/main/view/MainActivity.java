package com.pereira.tiago.desafio.mobile.main.view;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import me.rishabhkhanna.customtogglebutton.CustomToggleButton;

import static com.pereira.tiago.desafio.mobile.base.PaginationListener.PAGE_START;

public class MainActivity extends AppCompatActivity implements Contract.MainView,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainActivity";

    Toolbar toolbar;
    ImageView imgNoResults;
    View viewDivider;
    TextView txtNoResults;
    LinearLayout llType;
    CustomToggleButton buttonPopular, buttonUpcoming;
    RecyclerView rcvMovies;
    ProgressBar pbLoading;
    SwipeRefreshLayout swipeRefresh;
    private static  Contract.MainPresenter presenter;
    MainAdapter adapter;
    private int currentPage = PAGE_START;
    private String opSearch = Config.POPULAR;
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

        toolbar.setTitle("Desafio Mobile");
        toolbar.setSubtitle("Listagem de filmes");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                presenter.changeOption(opSearch, currentPage);
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
                opSearch = Config.UPCOMING;
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

        //presenter.getListMoviesPopular(currentPage);
        presenter.changeOption(opSearch, currentPage);
    }
}
