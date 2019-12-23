package com.ciet.leogg.filmes.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.model.Cast;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.presenter.MoviePresenter;
import com.ciet.leogg.filmes.presenter.MoviesContract;

import java.text.SimpleDateFormat;
import java.util.List;


public class DetailsFragment extends Fragment implements MoviesContract.DetailsView {
    private MoviesContract.MovieSelection movieSelection;
    private Movie movie;

    private NetworkImageView bigImage;
    private TextView name;
    private TextView genres;
    private TextView releaseYear;
    private TextView duration;
    private TextView cast;
    private TextView synopsis;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Movie movie) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.movie = movie;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieSelection = new MoviePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bigImage = view.findViewById(R.id.bigImage);
        name = view.findViewById(R.id.movie_name);
        genres = view.findViewById(R.id.genres);
        releaseYear = view.findViewById(R.id.release_year);
        duration = view.findViewById(R.id.duration);
        cast = view.findViewById(R.id.main_cast);
        synopsis = view.findViewById(R.id.synopsis);
    }

    @Override
    public void onResume() {
        super.onResume();
        movieSelection.loadMovie(movie);
    }

    @Override
    public void showMovie(Movie movie, List<Cast> castList, String genres) {
        bigImage.setDefaultImageResId(R.mipmap.ic_launcher);
        bigImage.setImageUrl("https://image.tmdb.org/t/p/original"+movie.getBackdropPath(), AppRequestQueue.getInstance().getImageLoader());
        name.setText(movie.getTitle());
        this.genres.setText(genres);
        releaseYear.setText((new SimpleDateFormat("yyyy")).format(movie.getReleaseDate()));
        duration.setText("");
        String castStr = "";
        for(Cast c:castList){
            castStr+=" "+c.getName();
        }
        cast.setText(castStr);
        synopsis.setText(movie.getOverview());

    }
}
