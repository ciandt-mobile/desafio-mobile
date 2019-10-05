package com.rbm.example.moviechallenge.app.feature.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.app.core.databiding.BindingHolder;
import com.rbm.example.moviechallenge.data.api.ApiClient;
import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.databinding.MovieItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<BindingHolder<MovieItemBinding>> {

    private static final String TAG = MovieListAdapter.class.getSimpleName();
    private List<MovieData> movieDataList;
    private MovieAdapterListener clickListener;

    public MovieListAdapter(List<MovieData> movieDataList, MovieAdapterListener clickListener) {
        this.movieDataList = movieDataList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public BindingHolder<MovieItemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.movie_item, parent, false);
        return new BindingHolder<MovieItemBinding>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder<MovieItemBinding> holder, int position) {
        MovieItemBinding binding = holder.getBinding();

        Picasso picasso = Picasso.get();
        picasso.load(ApiClient.IMAGE_URL + movieDataList.get(position).getPoster_path())
                .placeholder(R.drawable.image_not_found)
                .into(binding.movieImageView);
        binding.setMovie(movieDataList.get(position));
        binding.getRoot().setOnClickListener(v -> clickListener.onClick(movieDataList.get(position)));
    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }

    interface MovieAdapterListener {
        void onClick(MovieData movieData);
    }
}
