package com.pereira.tiago.desafio.mobile.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.pereira.tiago.desafio.mobile.R;
import com.pereira.tiago.desafio.mobile.databasemodels.Movie;
import com.pereira.tiago.desafio.mobile.main.Contract;
import com.pereira.tiago.desafio.mobile.main.presenter.Presenter;
import com.pereira.tiago.desafio.mobile.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.MainView {

    Toolbar toolbar;
    ImageView imgNoResults;
    View viewDivider;
    TextView txtNoResults;
    RecyclerView rcvMovies;
    private static  Contract.MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        presenter.getPermissions();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoResults() {
        rcvMovies.setVisibility(View.GONE);
        imgNoResults.setVisibility(View.VISIBLE);
        viewDivider.setVisibility(View.VISIBLE);
        txtNoResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPopulateRecycler(List<Movie> movies) {
        rcvMovies.setVisibility(View.VISIBLE);
        imgNoResults.setVisibility(View.GONE);
        viewDivider.setVisibility(View.GONE);
        txtNoResults.setVisibility(View.GONE);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, Utils.numberOfColums(this));
        rcvMovies.setLayoutManager(gridLayoutManager);
        MainAdapter adapter = new MainAdapter(movies);
        rcvMovies.setAdapter(adapter);
    }
}
