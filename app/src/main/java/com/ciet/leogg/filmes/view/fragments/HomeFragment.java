package com.ciet.leogg.filmes.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.presenter.HomePresenter;
import com.ciet.leogg.filmes.presenter.MoviesContract;

public class HomeFragment extends Fragment implements MoviesContract.HomeView {

    public HomeFragment() {}

    private MoviesContract.HomeInteraction homeInteraction;
    private View homeView;
    private TextView pageNumber;
    private TextView lastMovieName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeInteraction = new ViewModelProvider(this).get(HomePresenter.class);
        homeInteraction.setHomeView(this);
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        pageNumber = homeView.findViewById(R.id.home_page_number);
        lastMovieName = homeView.findViewById(R.id.home_last_movie_name);
        homeInteraction.loadPageAndLastMovie();
        return homeView;
    }


    @Override
    public void showPageAndLastMovie(Integer page, Movie movie) {
        pageNumber.setText(page.toString());
        String movieName = movie != null ? movie.getTitle() : "No movie selected yet";
        lastMovieName.setText(movieName);
    }
}
