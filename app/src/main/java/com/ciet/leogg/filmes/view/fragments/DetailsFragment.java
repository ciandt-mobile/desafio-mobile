package com.ciet.leogg.filmes.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.android.volley.toolbox.NetworkImageView;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.model.Cast;
import com.ciet.leogg.filmes.model.Genre;
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

    public DetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieSelection = new ViewModelProvider(this).get(MoviePresenter.class);
        movieSelection.setDetailsView(this);
        movie = DetailsFragmentArgs.fromBundle(getArguments()).getMovie();
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
    public void showMovie(Movie movie, List<Cast> castList, List<Genre> genreList) {
        bigImage.setDefaultImageResId(R.drawable.ic_baseline_sync_24);
        bigImage.setErrorImageResId(R.drawable.ic_baseline_sync_disabled_24);
        bigImage.setImageUrl("https://image.tmdb.org/t/p/original"+movie.getBackdropPath(), AppRequestQueue.getInstance().getImageLoader());
        name.setText(movie.getTitle());
        StringBuilder genreStr = new StringBuilder();
        for(Genre g:genreList){
            genreStr.append(g.getName()).append(", ");
        }
        genres.setText(genreStr.deleteCharAt(genreStr.length()-1).toString());
        releaseYear.setText((new SimpleDateFormat("yyyy")).format(movie.getReleaseDate()));
        duration.setText("");
        StringBuilder castStr = new StringBuilder();
        for(Cast c:castList){
            castStr.append(c.getName()).append(", ");
        }
        cast.setText(castStr.deleteCharAt(castStr.length()-1).toString());
        synopsis.setText(movie.getOverview());

    }
}
