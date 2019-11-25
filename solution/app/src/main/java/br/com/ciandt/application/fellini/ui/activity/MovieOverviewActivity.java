package br.com.ciandt.application.fellini.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ciandt.application.fellini.R;
import br.com.ciandt.application.fellini.domain.crew.Actor;
import br.com.ciandt.application.fellini.domain.crew.Credit;
import br.com.ciandt.application.fellini.domain.movie.Genre;
import br.com.ciandt.application.fellini.domain.movie.Movie;
import br.com.ciandt.application.fellini.service.MovieRepository;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingCreditsCallback;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingGenresCallback;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingMovieDetailsCallback;
import br.com.ciandt.application.fellini.ui.abstractactivity.AbstractActivity;
import br.com.ciandt.application.fellini.ui.datagroups.GeneralAdapter;
import br.com.ciandt.application.fellini.ui.datagroups.viewholders.ActorViewHolder;
import br.com.ciandt.application.fellini.utils.CollectionHandlingUtil;
import br.com.ciandt.application.fellini.utils.TimeUnitsUtil;

public class MovieOverviewActivity extends AbstractActivity {

    private static final String TAG = "movieoverview";

    private int movieId;

    private CollectionHandlingUtil<Actor> collectionUtil;
    private static final int MAIN_CREW_MAX_ENTRIES = 8;

    private ImageView movieOverviewBackdrop, moviePoster;

    private MaterialButton viewAllCrewButton;

    private RecyclerView mainCastRecyclerView;
    private GeneralAdapter<Actor> mainCastAdapter;

    private TextView mainCastHeaderTitle;

    private TextView movieReleaseDate, movieStatus, movieRuntime, movieTitle, movieReleaseDateHead, movieOverview, movieTagline, movieRating;

    private MovieRepository repository;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);

        enableWindowNoLimits();

        repository = MovieRepository.getInstance();

        movieId = getIntent().getIntExtra("movie_id", 0);

        mainCastRecyclerView = findViewById(R.id.grid_main_cast);

        collectionUtil = new CollectionHandlingUtil<>();

        movieTitle = findViewById(R.id.movie_title);
        movieReleaseDate = findViewById(R.id.release_date);
        movieReleaseDateHead = findViewById(R.id.movie_release_date);
        movieRuntime = findViewById(R.id.runtime);
        movieOverview = findViewById(R.id.movie_overview);
        movieStatus = findViewById(R.id.status);

        mainCastHeaderTitle = findViewById(R.id.main_cast_header_title);

        movieOverviewBackdrop = findViewById(R.id.overview_backdrop);
        moviePoster = findViewById(R.id.movie_poster);

        /*CrewService crewService = Client.getClient().create(CrewService.class);

        Call<Credit> movieCredits = crewService.getMovieCredits(movie.getId(), BuildConfig.THE_MOVIE_DATABASE_API_TOKEN);
        movieCredits.enqueue(new Callback<Credit>() {
            @Override
            public void onResponse(Call<Credit> call, Response<Credit> response) {
                List<Actor> cast = response.body().getCast();

                actorList = collectionUtil.limitListMaxViews(cast, MAIN_CREW_MAX_ENTRIES);

                if (actorList.isEmpty()) {
                    viewAllCrewButton.setEnabled(false);
                    viewAllCrewButton.setText(getString(R.string.more_button_no_cast_label));
                    mainCastHeaderTitle.setText("No Cast");
                }

                mainCastAdapter = new GeneralAdapter<Actor>(MovieOverviewActivity.this, actorList) {
                    @Override
                    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
                        return new ActorViewHolder(LayoutInflater.from(MovieOverviewActivity.this).inflate(R.layout.actor_item_layout, parent, false));
                    }

                    @Override
                    public void onBindData(RecyclerView.ViewHolder viewHolder, Actor item) {
                        ActorViewHolder actorViewHolder = (ActorViewHolder) viewHolder;
                        actorViewHolder.bind(item);
                    }
                };

                mainCastRecyclerView.setAdapter(mainCastAdapter);

            }

            @Override
            public void onFailure(Call<Credit> call, Throwable t) {

            }
        });

        MovieService movieService = Client.getClient().create(MovieService.class);

        Call<Movie> movieDetails = movieService.getMovieDetails(movie.getId(), BuildConfig.THE_MOVIE_DATABASE_API_TOKEN);
        Log.d(TAG, "URL: " + movieDetails.request().url());
        movieDetails.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieRuntime.setText(TimeUnitsUtil.convertRuntime(response.body().getRuntime()));
                movieReleaseDate.setText(movie.getReleaseDate(false));

                TextView movieStatus = findViewById(R.id.status);
                movieStatus.setText(response.body().getStatus());

                List<Genre> genres = response.body().getGenres();

                ChipGroup chipGroup = findViewById(R.id.genre_chip_group);

                for (int i = 0; i < genres.size(); i++) {
                    chipGroup.addView(inflateAndSetupChip(genres.get(i).getName(), chipGroup));
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });*/

        repository.getMovieDetails(movieId, new OnGettingMovieDetailsCallback() {
            @Override
            public void onSuccess(Movie movie) {
                ChipGroup chipGroup = findViewById(R.id.genre_chip_group);

                for (Genre genre : movie.getGenres()) {
                    chipGroup.addView(inflateAndSetupChip(genre.getName(), chipGroup));
                }

                Picasso.get().load(movie.getBackdropPath()).placeholder(R.drawable.ic_landscape_128dp).fit().centerCrop().into(movieOverviewBackdrop);
                Picasso.get().load(movie.getPosterPath()).placeholder(R.drawable.ic_movie_48dp).fit().centerCrop().into(moviePoster);
                movieTitle.setText(movie.getTitle());
                movieOverview.setText(movie.getOverview());
                movieReleaseDateHead.setText(movie.getReleaseDate(false));
                movieReleaseDate.setText(movie.getReleaseDate(false));
                movieRuntime.setText(TimeUnitsUtil.convertRuntime(movie.getRuntime()));
                movieStatus.setText(movie.getStatus());
            }

            @Override
            public void onError() {

            }
        });

        repository.getMovieCredits(movieId, new OnGettingCreditsCallback() {
            @Override
            public void onSuccess(Credit credit) {
                mainCastAdapter = new GeneralAdapter<Actor>(MovieOverviewActivity.this, collectionUtil.limitListMaxViews(credit.getCast(), MAIN_CREW_MAX_ENTRIES)) {
                    @Override
                    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
                        return new ActorViewHolder(LayoutInflater.from(MovieOverviewActivity.this).inflate(R.layout.actor_item_layout, parent, false));
                    }

                    @Override
                    public void onBindData(RecyclerView.ViewHolder viewHolder, Actor item) {
                        ActorViewHolder actorViewHolder = (ActorViewHolder) viewHolder;
                        actorViewHolder.bind(item);
                    }
                };

                mainCastRecyclerView.setAdapter(mainCastAdapter);
            }

            @Override
            public void onError() {

            }
        });

        viewAllCrewButton = findViewById(R.id.view_all_crew_button);
        viewAllCrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieOverviewActivity.this, CrewOverviewActivity.class);
                intent.putExtra("movie_id", movieId);
                startActivity(intent);
            }
        });

    }

    private View inflateAndSetupChip(String filterName, ViewGroup parent) {
        Chip chipView = (Chip) LayoutInflater.from(this).inflate(R.layout.chip_genre_layout, parent, false);
        chipView.setText(filterName);
        return chipView;
    }

}
