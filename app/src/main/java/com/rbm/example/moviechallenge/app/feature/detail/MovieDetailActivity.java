package com.rbm.example.moviechallenge.app.feature.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.data.api.ApiClient;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    public static final String EXTRA_TITLE = "com.rbm.example.moviechallenge.EXTRA_TITLE";
    public static final String EXTRA_OVERVIEW = "com.rbm.example.moviechallenge.EXTRA_OVERVIEW";
    public static final String EXTRA_RELEASE_DATE = "com.rbm.example.moviechallenge.EXTRA_RELEASE_DATE";
    public static final String EXTRA_BACKDROP_PATH = "com.rbm.example.moviechallenge.EXTRA_BACKDROP_PATH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        /*int movieId = getIntent().getIntExtra("id", 1);
        MovieDetailViewModel viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        viewModel.loadMovieDetail(movieId);
        viewModel.viewState.observe(this, this::onMovieUpdate);*/

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String release_date = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        String backdropPath = getIntent().getStringExtra(EXTRA_BACKDROP_PATH);

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
    }

    /*private void onMovieUpdate(MovieDetailData movieDetailData) {
        Log.d(TAG, movieDetailData.getTitle());
    }*/
}
