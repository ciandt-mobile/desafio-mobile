package com.ciet.leogg.filmes.view.fragments;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.presenter.MoviePresenter;
import com.ciet.leogg.filmes.presenter.MoviesContract;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        bigImage = view.findViewById(R.id.big_image);
        name = view.findViewById(R.id.movie_name);
        name.setText("••••••••");
        genres = view.findViewById(R.id.genres);
        genres.setText("••••••••");
        releaseYear = view.findViewById(R.id.release_year);
        releaseYear.setText("••••");
        duration = view.findViewById(R.id.duration);
        duration.setText("•••");
        cast = view.findViewById(R.id.main_cast);
        cast.setText("••••••••");
        synopsis = view.findViewById(R.id.synopsis);
        synopsis.setText("••••••••");
    }

    @Override
    public void onResume() {
        super.onResume();
        movieSelection.loadMovie(movie);
    }

    @Override
    public void showMovie(Movie movie, List<Cast> castList) {
        bigImage.setDefaultImageResId(R.drawable.ic_sync_landscape);
        bigImage.setErrorImageResId(R.drawable.ic_sync_disabled_landscape);
        String path = movie.getBackdropPath() == null
                ?"https://via.placeholder.com/900x600/bcaaa4/bcaaa4.png"
                :"https://image.tmdb.org/t/p/original"+movie.getBackdropPath();
        bigImage.setImageUrl(path, AppRequestQueue.getInstance().getImageLoader());
        name.setText(movie.getTitle());
        genres.setText(TextUtils.join(", ",movie.getGenres()));
        releaseYear.setText((new SimpleDateFormat("yyyy"))
                .format(movie.getReleaseDate() == null ? new Date(1400163380494L) : movie.getReleaseDate()));
        duration.setText(movie.getRuntime() == null ? "Unknown Duration" : movie.getRuntime()+"min");
        StringBuilder castStr = new StringBuilder();
        for(int i=0;i<castList.size();i++){
            castStr.append(castList.get(i).getName());
            if(i != castList.size()-1){
                castStr.append(", ");
            }
        }
        cast.setText(castStr.toString());
        synopsis.setText(movie.getOverview());
    }
}
