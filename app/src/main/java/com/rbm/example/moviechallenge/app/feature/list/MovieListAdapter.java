package com.rbm.example.moviechallenge.app.feature.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbm.example.moviechallenge.R;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private String[] testDataset;

    public MovieListAdapter(String[] testDataset) {
        this.testDataset = testDataset;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_movie_name);
        }
    }

    @NonNull
    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.movie_item_view, parent, false);

        MovieViewHolder viewHolder = new MovieViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.textView.setText(testDataset[position]);
    }

    @Override
    public int getItemCount() {
        return testDataset.length;
    }
}
