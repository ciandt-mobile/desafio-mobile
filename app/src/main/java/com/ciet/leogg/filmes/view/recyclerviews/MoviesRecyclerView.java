package com.ciet.leogg.filmes.view.recyclerviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.ciet.leogg.filmes.NavGraphDirections;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.model.Movie;
import com.ciet.leogg.filmes.view.customviews.MovieItemView;

import java.util.Objects;

public class MoviesRecyclerView extends RecyclerView {
    public MoviesRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public MoviesRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoviesRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setAdapter(new MoviesAdapter());
        this.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    public MoviesAdapter getMoviesAdapter(){
        return (MoviesAdapter) this.getAdapter();
    }

    public static class MoviesAdapter extends ListAdapter<Movie,MoviesViewHolder> {
        public MoviesAdapter(){
            super(diffUtilItemCallback);
        }

        @NonNull
        @Override
        public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View movieItemView = inflater.inflate(R.layout.movie_item_view, parent, false);
            return new MoviesViewHolder(movieItemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MoviesViewHolder holder, final int position) {
            holder.updateMovieItemView(getItem(position));
            holder.movieItemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavDirections direction = NavGraphDirections.actionGlobalDetailsFragment(getItem(position));
                    Navigation.findNavController(view).navigate(direction);
                }
            });
        }
    }
    private static class MoviesViewHolder extends ViewHolder{
        private final MovieItemView movieItemView;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            movieItemView = itemView.findViewById(R.id.movie_item_view);
        }
        public void updateMovieItemView(Movie movie){
            if(movie == null){
                movie = Movie.createDefault();
            }
            movieItemView.updateMovie(movie);
        }
    }


    private static final DiffUtil.ItemCallback<Movie> diffUtilItemCallback = new DiffUtil.ItemCallback<Movie>(){
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };

}
