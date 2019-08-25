package com.svmdev.appmovies.view.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.svmdev.appmovies.R;
import com.svmdev.appmovies.help.Variables;
import com.svmdev.appmovies.view.adapter.CastAdapter;

public class DetailActivity extends AppCompatActivity implements DetailInterface {

    private Toolbar toolbar;
    private ImageView image;
    private TextView title;
    private TextView date;
    private TextView duration;
    private TextView genres;
    private TextView overview;
    private ProgressBar progress;

    private DetailPresenter mPresenter;

    private RecyclerView castList;
    private CastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.details_toolbar);
        image = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        date = findViewById(R.id.detail_date);
        duration = findViewById(R.id.detail_duration);
        genres = findViewById(R.id.detail_genres);
        castList = findViewById(R.id.detail_cast_list);
        overview = findViewById(R.id.detail_overview_text);
        progress = findViewById(R.id.detail_progressbar_loading);

        Intent data = getIntent();
        int movieId =  data.getExtras().getInt("movie_id");

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        toolbar.setTitle("Detalhes do Filme");

        mPresenter = new DetailPresenter(this);

        mPresenter.loadCast(movieId);
        mPresenter.loadMovieDetail(movieId);
    }

    //sobrescreve o botão de voltar da barra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Dicionario de dados
                Variables.movieCast.clear();
                Intent result = new Intent();
                this.setResult(Activity.RESULT_OK, result);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setListAdapter(){
        adapter = new CastAdapter(Variables.movieCast, getApplication());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        castList.setLayoutManager(horizontalLayoutManager);
        castList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public Activity activity() {
        return this;
    }

    @Override
    public ImageView image() {
        return image;
    }

    @Override
    public TextView title() {
        return title;
    }

    @Override
    public TextView date() {
        return date;
    }

    @Override
    public TextView duration() {
        return duration;
    }

    @Override
    public TextView genres() {
        return genres;
    }

    @Override
    public TextView overview() {
        return overview;
    }

    @Override
    public ProgressBar progress() {
        return progress;
    }

}
