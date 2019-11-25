package br.com.ciandt.application.fellini.ui.datagroups.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.ciandt.application.fellini.R;
import br.com.ciandt.application.fellini.domain.movie.Movie;
import br.com.ciandt.application.fellini.ui.datagroups.GenericViewHolder;
import br.com.ciandt.application.fellini.ui.datagroups.OnClickListener;

public class MovieViewHolder extends GenericViewHolder<Movie> {

    private static final String TAG = "movieadapter";

    private LinearLayout adultBadge;
    private CardView itemCardView;
    private ImageView movieBanner;
    private TextView movieTitle, movieReleaseDate;
    private OnClickListener onClickListener;
    
    public MovieViewHolder(@NonNull View itemView, final OnClickListener onClickListener) {
        super(itemView);

        this.onClickListener = onClickListener;

        itemCardView = itemView.findViewById(R.id.movie_item_cardview);
        movieBanner = itemView.findViewById(R.id.movie_item_picture);
        movieTitle = itemView.findViewById(R.id.movie_item_title);
        movieReleaseDate = itemView.findViewById(R.id.movie_item_release_date);
        adultBadge = itemView.findViewById(R.id.adult_badge);

        if (onClickListener != null) {
            itemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClickListener(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void bind(Movie movie) {
        if (!movie.isAdult()) {
            adultBadge.setVisibility(View.GONE);
        }

        Picasso.get().load(movie.getPosterPath()).placeholder(R.drawable.ic_movie_48dp).fit().into(movieBanner);
        movieTitle.setText(movie.getTitle());
        movieReleaseDate.setText(movie.getReleaseDate(true));
    }

}
