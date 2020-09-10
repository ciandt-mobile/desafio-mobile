package com.ciet.leogg.filmes.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.databinding.FragmentDetailsBinding;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.presenter.MoviePresenter;
import com.ciet.leogg.filmes.view.customviews.MyNetworkImageView;


public class DetailsFragment extends Fragment {

    public DetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MoviePresenter presenter = new ViewModelProvider(this).get(MoviePresenter.class);
        FragmentDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        binding.setLifecycleOwner(this);
        binding.setPresenter(presenter);
        Movie movie = DetailsFragmentArgs.fromBundle(getArguments()).getMovie();
        presenter.loadMovie(movie);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MyNetworkImageView)view.findViewById(R.id.big_image)).setDefaultImageResId(R.drawable.ic_sync_landscape);
    }
}
