package com.ceandt.moviedb.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ceandt.moviedb.R;
import com.ceandt.moviedb.adapter.DetailMovieAdapter;
import com.ceandt.moviedb.application.ApplicationManager;
import com.ceandt.moviedb.arch.presenter.MovieDbPresenter;
import com.ceandt.moviedb.arch.view.DetailMovieView;
import com.ceandt.moviedb.model.DetailMovie;
import com.ceandt.moviedb.model.Genre;
import com.ceandt.moviedb.util.DateUtils;
import com.ceandt.moviedb.util.PicassoUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView {

    private static final String GENRE_DELIMITER = ", ";
    private static final double HEIGHT_FACTOR = 1.2;
    private static final int DEFAULT_MOVIE_ID_EXTRA = -1;

    @BindView(R.id.iv_poster_path)
    ImageView mIvPosterPath;

    @BindView(R.id.tv_synopsis)
    TextView mTvSynopsis;

    @BindView(R.id.tv_year)
    TextView mTvYear;

    @BindView(R.id.tv_runtime)
    TextView mTvRuntime;

    @BindView(R.id.tv_genres)
    TextView mTvGenres;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.rv_cast)
    RecyclerView mRvCast;

    @Inject
    MovieDbPresenter mPresenter;

    Unbinder mUnbinder;

    DetailMovieAdapter mDetailMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        init();
    }

    private void init() {
        mUnbinder = ButterKnife.bind(this);
        inject();
        getExtraAndLoadDetailMovie();
        setUpToolbar();
        setUpRecyclerView();
    }

    private void getExtraAndLoadDetailMovie() {
        int movieId = getIntent().getIntExtra(MainActivity.MOVIE_ID_EXTRA, DEFAULT_MOVIE_ID_EXTRA);
        if (movieId != -1) {
            mPresenter.setView(this);
            mPresenter.loadMovieDetail(movieId);
        }
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setUpRecyclerView() {
        mDetailMovieAdapter = new DetailMovieAdapter(this);
        mRvCast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRvCast.setAdapter(mDetailMovieAdapter);
    }

    private void inject() {
        ((ApplicationManager) getApplication()).getMainComponent().inject(this);
    }

    @Override
    public void loadDetailMovie(DetailMovie detailMovie) {
        mTvTitle.setText(detailMovie.getTitle());
        mTvYear.setText(DateUtils.getYear(detailMovie.getReleaseDate()));
        mTvSynopsis.setText(detailMovie.getOverview());
        mTvRuntime.setText(getString(R.string.runtime, detailMovie.getRuntime()));
        mDetailMovieAdapter.setCasts(detailMovie.getCredits().getCast());
        setGenres(detailMovie.getGenres());
        loadImage(detailMovie.getPosterPath());
    }

    private void loadImage(final String posterPath) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = (int) (displayMetrics.widthPixels * HEIGHT_FACTOR);
        final int width = displayMetrics.widthPixels;
        PicassoUtils.loadImage(this, mIvPosterPath, posterPath, width, height);
    }

    private void setGenres(List<Genre> genres) {
        String names = genres.stream().map(Genre::getName).collect(Collectors.joining(GENRE_DELIMITER));
        mTvGenres.setText(names);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mPresenter.dispose();
    }
}
