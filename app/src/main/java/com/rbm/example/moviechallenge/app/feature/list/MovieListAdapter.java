package com.rbm.example.moviechallenge.app.feature.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.app.core.databiding.BindingHolder;
import com.rbm.example.moviechallenge.data.data.MovieData;
import com.rbm.example.moviechallenge.databinding.MovieItemBinding;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<BindingHolder<MovieItemBinding>> {

    private List<MovieData> movieDataList;

    public MovieListAdapter(List<MovieData> movieDataList) {
        this.movieDataList = movieDataList;
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
        binding.setMovie(movieDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }
}
