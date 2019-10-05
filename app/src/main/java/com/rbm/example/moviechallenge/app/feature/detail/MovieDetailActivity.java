package com.rbm.example.moviechallenge.app.feature.detail;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.data.api.ApiClient;
import com.rbm.example.moviechallenge.data.data.MovieDetailData;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    public static final String EXTRA_TITLE = "com.rbm.example.moviechallenge.EXTRA_TITLE";
    public static final String EXTRA_OVERVIEW = "com.rbm.example.moviechallenge.EXTRA_OVERVIEW";
    public static final String EXTRA_RELEASE_DATE = "com.rbm.example.moviechallenge.EXTRA_RELEASE_DATE";
    public static final String EXTRA_BACKDROP_PATH = "com.rbm.example.moviechallenge.EXTRA_BACKDROP_PATH";
    public static final String EXTRA_ID = "com.rbm.example.moviechallenge.EXTRA_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        int movieId = getIntent().getIntExtra(EXTRA_ID, 1);
        MovieDetailViewModel viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        viewModel.loadMovieDetail(movieId);
        viewModel.viewState.observe(this, this::onMovieUpdate);
    }

    private void onMovieUpdate(MovieDetailData movieDetailData) {
        Log.d(TAG, movieDetailData.getTitle());

        String title = movieDetailData.getTitle();
        String overview = movieDetailData.getOverview();
        String release_date = extractYear(movieDetailData.getRelease_date());
        String backdropPath = movieDetailData.getBackdrop_path();
        String runtime = Integer.toString(movieDetailData.getRuntime());
        List<MovieDetailData.Genre> genres = movieDetailData.getGenres();
        String genresString = "";

        for (MovieDetailData.Genre genre : genres) {
            genresString = String.join(",", genre.getName());
        }

        ImageView movieImage = findViewById(R.id.movie_detail_image_view);

        Picasso picasso = Picasso.get();
        picasso.load(ApiClient.IMAGE_LARGE_URL + backdropPath)
                .placeholder(R.drawable.image_not_found)
                .into(movieImage);

        TextView titleTextView = findViewById(R.id.movie_title_text_view);
        titleTextView.setText(title);

        TextView overviewTextView = findViewById(R.id.movie_overview_text_view);
        overviewTextView.setText(overview);

        TextView releaseDateTextView = findViewById(R.id.movie_release_text_view);
        releaseDateTextView.setText(release_date);

        TextView runtimeTextView = findViewById(R.id.movie_runtime_text_view);
        runtimeTextView.setText(runtime);

        TextView genresTextView = findViewById(R.id.movie_genres_text_view);
        genresTextView.setText(genresString);
    }

    String extractYear(String dateString) {
        int year = 2019;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString);
            Calendar cal = Calendar.getInstance();
            if (date != null) {
                cal.setTime(date);
                year = cal.get(Calendar.YEAR);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.toString(year);
    }
}
