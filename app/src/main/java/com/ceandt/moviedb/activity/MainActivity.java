package com.ceandt.moviedb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ceandt.moviedb.R;
import com.ceandt.moviedb.adapter.MovieAdapter;
import com.ceandt.moviedb.application.ApplicationManager;
import com.ceandt.moviedb.arch.presenter.MovieDbPresenter;
import com.ceandt.moviedb.arch.view.MoviesView;
import com.ceandt.moviedb.model.Movie;
import com.ceandt.moviedb.preferences.Preferences;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MoviesView {

    public static final String MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA";

    @BindView(R.id.tv_first_group)
    TextView mTvFirstGroup;

    @BindView(R.id.rv_first_group)
    RecyclerView mRvFirstGroup;

    @BindView(R.id.rv_second_group)
    RecyclerView mRvSecondGroup;

    @BindView(R.id.rv_third_group)
    RecyclerView mRvThirdGroup;

    @BindView(R.id.st_preference)
    Switch mSwitchPreference;

    @Inject
    MovieDbPresenter mPresenter;

    Unbinder mUnbinder;

    private MovieAdapter mAdapterFirstGroup, mAdapterSecondGroup, mAdapterThirdGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        inject();
        mAdapterFirstGroup = setUpRecyclerView(mRvFirstGroup);
        mAdapterSecondGroup = setUpRecyclerView(mRvSecondGroup);
        mAdapterThirdGroup = setUpRecyclerView(mRvThirdGroup);
        mAdapterFirstGroup.setListener(((Movie model, int position) -> startActivity(model.getId())));
        setUpSwitchPreference();
        mPresenter.setView(this);
        mPresenter.loadMovies(getMoviePreference());
    }

    private void inject() {
        ((ApplicationManager) getApplication()).getMainComponent().inject(this);
    }

    private MovieAdapter setUpRecyclerView(final RecyclerView rv) {
        MovieAdapter adapter = new MovieAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rv.setAdapter(adapter);
        return adapter;
    }

    private void setUpSwitchPreference() {
        setCheckAndTextSwitcherByPreference();
        mSwitchPreference.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int switchResId, firstGroupResId;
            if (isChecked) {
                Preferences.putMoviePreference(this, Preferences.POPULAR_VALUE);
                switchResId = R.string.popular;
                firstGroupResId = R.string.popular_movie;
            } else {
                switchResId = R.string.upcoming;
                firstGroupResId = R.string.upcoming;
                Preferences.putMoviePreference(this, Preferences.UPCOMING_VALUE);
            }
            mSwitchPreference.setText(getString(switchResId));
            mTvFirstGroup.setText(getString(firstGroupResId));
            mPresenter.switchPreference(Preferences.getMoviePreference(this));
        });
    }

    private void setCheckAndTextSwitcherByPreference() {
        if (getMoviePreference().equals(Preferences.UPCOMING_VALUE)) {
            mSwitchPreference.setChecked(false);
            mSwitchPreference.setText(getString(R.string.upcoming));
            mTvFirstGroup.setText(getString(R.string.upcoming));
        }
    }


    private String getMoviePreference() {
        return Preferences.getMoviePreference(this);
    }

    private void startActivity(final int movieId) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra(MOVIE_ID_EXTRA, movieId);
        startActivity(intent);
    }

    @Override
    public void onLoadNowPlayingMovies(List<Movie> movies) {
        mAdapterSecondGroup.setMovies(movies);
    }

    @Override
    public void onLoadTopRatedMovies(List<Movie> movies) {
        mAdapterThirdGroup.setMovies(movies);
    }

    @Override
    public void onLoadUpComingOrPopularMovies(List<Movie> movies) {
        mAdapterFirstGroup.setMovies(movies);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dispose();
        mUnbinder.unbind();
    }
}
