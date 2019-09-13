package com.rangeldor.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rangeldor.movieapp.R;
import com.rangeldor.movieapp.api.MovieClient;
import com.rangeldor.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewDetailAdapter extends RecyclerView.Adapter<RecyclerViewDetailAdapter.RecyclerViewHolder> {

    private List<Movie.Detail.Cast> casts;
    private Context context;

    public RecyclerViewDetailAdapter(List<Movie.Detail.Cast> casts, Context context) {
        this.casts = casts;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewDetailAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_recycler_detail,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewDetailAdapter.RecyclerViewHolder viewHolder, int i) {

        String strDetailThumb = MovieClient.BASE_IMAGE_URL + casts.get(i).getProfilePath ();
        Picasso.get ( ).load ( strDetailThumb ).placeholder(R.drawable.ic_circle).into ( viewHolder.profile_image );

        StringBuilder detail_cast = new StringBuilder ( );

        detail_cast.append ( casts.get ( i ).getName ( ) )
                .append ( "\n( " )
                .append ( casts.get ( i ).getCharacter ( ) )
                .append ( " )" );

        viewHolder.movie_cast.setText ( detail_cast );
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image)
        ImageView profile_image;

        @BindView(R.id.movie_cast)
        TextView movie_cast;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
