package com.example.movies.src.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.src.controller.MovieController;
import com.example.movies.src.model.MovieBean;
import com.example.movies.src.utils.Logger;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoviesAdapter extends BaseAdapter {
        private Activity activity;
        private List<MovieBean> movies;
        private static final String TAG = "BaseAdapter";
        private MovieController movieController;
        private String type;

        public static final String TYPE_POPULAR = "popular";
        public static final String TYPE_UPCOMING = "upcoming";
        public static final String TYPE_FAVOURITE = "fav";

        public MoviesAdapter(Activity a, List<MovieBean> d, MovieController ctrl, String type) {
            activity = a;
            movies = d;
            movieController = ctrl;
            this.type = type;

            GridView rootLayout = (GridView) activity.findViewById(R.id.moviesGridView);
            rootLayout.setAdapter(this);
            rootLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    Logger.log(TAG, movies.get(position).toString());
                    movieController.showDetailedMovieView(movies.get(position));
                }
            });
        }
        public int getCount() {
            return movies.size();
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (TYPE_POPULAR.equals(type) && position == movies.size()-1) {
                movieController.getNextMoviesPage();
                return getView(position,  convertView, parent);
            }
            MoviesViewHolder holder = null;
            if (convertView == null) {
                holder = new MoviesViewHolder();
                convertView = LayoutInflater.from(activity).inflate(R.layout.basic_movies, parent, false);

                holder.movieImg = (ImageView) convertView.findViewById(R.id.basicMovieImg);
                holder.favIcon = (ImageView) convertView.findViewById(R.id.likeImg);
                holder.movieName = (TextView) convertView.findViewById(R.id.movieName);
                holder.releaseDate = (TextView) convertView.findViewById(R.id.releaseDate);

                convertView.setTag(holder);
            } else {
                holder = (MoviesViewHolder) convertView.getTag();
            }
            holder.movieImg.setId(position);
            holder.movieName.setId(position);
            holder.releaseDate.setId(position);
            holder.favIcon.setId(position);

            final MovieBean movie = movies.get(position);
            try {
                holder.movieName.setText(movie.getName());
                holder.releaseDate.setText(movie.getReleaseDateToString());
                SharedPreferences sharedPreference = activity.getSharedPreferences("favMoviesIds", Context.MODE_PRIVATE);
                Set<String> ids = sharedPreference.getStringSet("favMoviesIds", new HashSet<String>());
                if (ids.contains(movie.getID())) {
                    movie.setIsfavourite(true);
                }
                holder.favIcon.setImageResource(movie.isFavourite() ? R.drawable.heart_clicked : R.drawable.heart);
                Picasso.get().load(movie.getPosterURL()).into(holder.movieImg);

                holder.favIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movie.setIsfavourite(!movie.isFavourite());
                        if (movie.isFavourite()) {
                            ((ImageView)v).setImageResource(R.drawable.heart_clicked);
                            movieController.addFavouriteMovie(movie);
                        } else {
                            ((ImageView)v).setImageResource(R.drawable.heart);
                            movieController.removeFavouriteMovie(movie);
                        }
                    }
                });


            } catch (Exception e) {}
            return convertView;
        }

        class MoviesViewHolder {
            ImageView movieImg, favIcon;
            TextView movieName, releaseDate;
        }
    }

