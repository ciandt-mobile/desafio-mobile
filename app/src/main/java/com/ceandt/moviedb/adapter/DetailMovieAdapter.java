package com.ceandt.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ceandt.moviedb.R;
import com.ceandt.moviedb.model.Cast;
import com.ceandt.moviedb.util.PicassoUtils;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieAdapter extends RecyclerView.Adapter<DetailMovieAdapter.MovieDbViewHolder> {

    private final WeakReference<Context> weakReference;
    private List<Cast> casts;

    public DetailMovieAdapter(final Context context) {
        this.weakReference = new WeakReference<>(context);
    }

    @NonNull
    @Override
    public MovieDbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(weakReference.get()).inflate(R.layout.movie_detail_recycler_view, parent, false);
        return new MovieDbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDbViewHolder holder, int position) {
        Cast model = casts.get(position);
        PicassoUtils.loadImageWithCircleTransformation(weakReference.get(), holder.ivPosterPath, model.getProfilePath());
    }

    @Override
    public int getItemCount() {
        return casts != null ? casts.size() : 0;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts.stream().filter(it -> Objects.nonNull(it.getProfilePath())).collect(Collectors.toList());
        notifyDataSetChanged();
    }

    static class MovieDbViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster_path)
        ImageView ivPosterPath;

        MovieDbViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
