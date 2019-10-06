package com.example.movies.src.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.src.model.MovieBean;
import com.example.movies.src.model.PersonBean;
import com.example.movies.src.utils.Logger;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailedMovieView extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "DetailedMovieView";

    MovieBean movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_movie);

        movie = new MovieBean(new Gson().fromJson(getIntent().getStringExtra("movie"), JsonObject.class),
                new Gson().fromJson(getIntent().getStringExtra("config"), JsonObject.class));
        movie.setDetailedMovieInfo(new Gson().fromJson(getIntent().getStringExtra("movie"), JsonObject.class));

        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.videoView);
        if (movie.getVideoKey() != null) {
            youTubeView.initialize(PlayerConfig.API_KEY, this);
        } else {
            findViewById(R.id.videoViewLayout).setVisibility(View.GONE);
        }

        ImageView originalImage = findViewById(R.id.originalImage);
        Logger.log(TAG, movie.getOriginalPosterURL());
        Picasso.get().load(movie.getOriginalPosterURL()).into(originalImage);

        TextView movieName = findViewById(R.id.detailedMovieName);
        movieName.setText(movie.getName());

        TextView movieYear = findViewById(R.id.detailedMovieYear);
        movieYear.setText(movie.getMovieYear());

        TextView movieOverview = findViewById(R.id.detailedMovieOverview);
        movieOverview.setText(movie.getMovieOverview());

        TextView movieGenres = findViewById(R.id.detailedMovieGenres);
        movieGenres.setText(movie.getRunTime() + "  " + getString(R.string.minutes) + " | " + movie.getGenresNames());

        RecyclerView recyclerView = findViewById(R.id.detailedMovieCast);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new CastAdapter(this.getApplicationContext(), movie.getCast()));
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(movie.getVideoKey());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private class PlayerConfig {
        PlayerConfig(){}
        public static final String API_KEY = "AIzaSyDCvd7fQIT5CL_AYTWeFTFGdcuh2IaHp_c";
    }

    private class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

        private LayoutInflater mInflater;
        private List<PersonBean> mCast;
        private static final String TAG = "CastAdapter";

        // data is passed into the constructor
        public CastAdapter(Context context, List<PersonBean> cast) {
            this.mInflater = LayoutInflater.from(context);
            this.mCast = cast;

        }

        // inflates the row layout from xml when needed
        @Override
        public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.movie_cast, parent, false);
            return new CastViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(CastViewHolder holder, int position) {
            PersonBean person = mCast.get(position);
            holder.castName.setText(person.getName());
            holder.castCharacter.setText(person.getCharacter());
            Picasso.get().load(person.getImgRUL()).into(holder.castImg);
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return 5;
        }

        class CastViewHolder extends RecyclerView.ViewHolder {
            ImageView castImg;
            TextView castName, castCharacter;

            public CastViewHolder(@NonNull View itemView) {
                super(itemView);
                castImg = (ImageView) itemView.findViewById(R.id.movieCastImg);
                castName = (TextView) itemView.findViewById(R.id.personName);
                castCharacter = (TextView) itemView.findViewById(R.id.personCharacter);
            }
        }
    }
}
