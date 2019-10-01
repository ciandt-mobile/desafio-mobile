package com.pereira.tiago.desafio.mobile.main.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pereira.tiago.desafio.mobile.R;
import com.pereira.tiago.desafio.mobile.base.Config;
import com.pereira.tiago.desafio.mobile.databasemodels.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainHolder> {

    private List<Movie> movieList;

    public MainAdapter(List<Movie> movies) {
        this.movieList = movies;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        final Movie movie = movieList.get(position);

        holder.txtName.setText(movie.getTitle());
        holder.txtDate.setText(movie.getRelease_date());

        Picasso.get()
                .load(Config.BASE_URL_IMG + movie.getPoster_path())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
