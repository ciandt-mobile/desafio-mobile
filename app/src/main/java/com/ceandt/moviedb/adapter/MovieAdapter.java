package com.ceandt.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ceandt.moviedb.R;
import com.ceandt.moviedb.model.Movie;
import com.ceandt.moviedb.util.DateUtils;
import com.ceandt.moviedb.util.PicassoUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieDbViewHolder> {

    private final WeakReference<Context> weakReference;
    private List<Movie> movies;
    private OnItemClickListener<Movie> listener;

    public MovieAdapter(final Context context) {
        this.weakReference = new WeakReference<>(context);
    }

    @NonNull
    @Override
    public MovieDbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(weakReference.get()).inflate(R.layout.movie_recycler_view, parent, false);
        return new MovieDbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDbViewHolder holder, int position) {
        Movie model = movies.get(position);
        holder.llMain.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClickItem(model, position);
            }
        });
        holder.tvReleaseDate.setText(DateUtils.formatDate(model.getReleaseDate()));
        holder.tvTitle.setText(model.getTitle());
        PicassoUtils.loadImage(weakReference.get(), holder.ivPosterPath, model.getPosterPath());
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener<Movie> listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener<T> {
        void onClickItem(T model, int position);
    }

    static class MovieDbViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_main)
        LinearLayout llMain;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_release_date)
        TextView tvReleaseDate;

        @BindView(R.id.iv_poster_path)
        ImageView ivPosterPath;

        MovieDbViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
