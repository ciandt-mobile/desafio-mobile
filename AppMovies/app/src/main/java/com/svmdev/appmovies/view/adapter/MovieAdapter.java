package com.svmdev.appmovies.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.svmdev.appmovies.R;
import com.svmdev.appmovies.data.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {

    Context context;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
        this.context = context;
    }

    public View getView(int pos, View view, ViewGroup parent) {

        //make this cell is recyclable
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_movie, parent, false);
        }

        Movie movie = this.getItem(pos);

        ImageView picture = view.findViewById(R.id.adapt_movie_pic);
        TextView title = view.findViewById(R.id.adapt_movie_title);
        TextView date = view.findViewById(R.id.adapt_movie_release);

        title.setText(movie.getTitle());
        date.setText(movie.getFormatedReleaseDate());

        if(!movie.getPosterPath().isEmpty()) {
            Picasso.with(context).load(movie.getPosterPath())
                    .placeholder(R.drawable.download)
                    .error(R.drawable.no_image)
                    .into(picture, new com.squareup.picasso.Callback() {

                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }


                    });
        } else {
            picture.setImageResource(R.drawable.no_image);
        }

        return view;
    }

}
