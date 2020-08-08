package com.ciet.leogg.filmes.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.presenter.MoviesContract;
import com.ciet.leogg.filmes.presenter.TabPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class PopularFragment extends Fragment implements MoviesContract.ListView {
    private MoviesContract.TabInteraction tabInteraction;
    private MoviesAdapter moviesAdapter;

    public PopularFragment() {
        // Required empty public constructor
    }

    public static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabInteraction = TabPresenter.getInstance().setPopularView(this);
        moviesAdapter = new MoviesAdapter(new ArrayList<Movie>(),itemListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.popular_list);
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onResume() {
        super.onResume();
        tabInteraction.loadMovies();
    }

    @Override
    public void showMovies(List<Movie> movies) {
        moviesAdapter.setList(movies);
    }

    ItemListener itemListener = new ItemListener() {
        @Override
        public void onMovieClick(Movie movie) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_frame, DetailsFragment.newInstance(movie))
                    .addToBackStack(null)
                    .commit();
        }
    };

    private static class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

        private List<Movie> movies;
        private ItemListener itemListener;

        public MoviesAdapter(List<Movie> movies, ItemListener itemListener) {
            setList(movies);
            this.itemListener = itemListener;
        }

        @Override
        public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.movie_item, parent, false);

            return new MoviesAdapter.ViewHolder(noteView, itemListener);
        }

        @Override
        public void onBindViewHolder(MoviesAdapter.ViewHolder viewHolder, int position) {
            Movie movie = movies.get(position);
            viewHolder.thumbnail.setDefaultImageResId(R.mipmap.ic_launcher);
            viewHolder.thumbnail.setImageUrl("https://image.tmdb.org/t/p/w500"+movie.getPosterPath(), AppRequestQueue.getInstance().getImageLoader());
            viewHolder.title.setText(movie.getTitle());
            viewHolder.releaseDate.setText((new SimpleDateFormat("dd/mm/yyyy").format(movie.getReleaseDate())));
        }

        public void replaceData(List<Movie> notes) {
            setList(notes);
            notifyDataSetChanged();
        }

        private void setList(List<Movie> notes) {
            movies = notes;
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

        public Movie getItem(int position) {
            return movies.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public NetworkImageView thumbnail;
            public TextView title;
            public TextView releaseDate;
            private ItemListener mItemListener;

            public ViewHolder(View itemView, ItemListener listener) {
                super(itemView);
                mItemListener = listener;
                title = (TextView) itemView.findViewById(R.id.movie_title);
                thumbnail = (NetworkImageView) itemView.findViewById(R.id.movie_thumbnail);
                releaseDate = (TextView) itemView.findViewById(R.id.movie_release_date);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Movie movie = getItem(position);
                mItemListener.onMovieClick(movie);
            }
        }
    }

    public interface ItemListener {
        void onMovieClick(Movie clickedNote);
    }

}

