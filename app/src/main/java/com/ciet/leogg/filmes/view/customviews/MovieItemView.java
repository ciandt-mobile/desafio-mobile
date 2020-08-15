package com.ciet.leogg.filmes.view.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import com.android.volley.toolbox.NetworkImageView;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.model.Movie;

import java.text.SimpleDateFormat;

public class MovieItemView extends CardView{
    private NetworkImageView thumbnail;
    private TextView releaseDate;
    private TextView title;
    public MovieItemView(@NonNull Context context) {
        super(context);
    }

    public MovieItemView(@NonNull Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieItemView(@NonNull Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        thumbnail = this.findViewById(R.id.movie_thumbnail);
        releaseDate = this.findViewById(R.id.movie_release_date);
        title = this.findViewById(R.id.movie_title);
    }

    public void updateMovie(Movie movie){
        thumbnail.setDefaultImageResId(R.drawable.ic_baseline_sync_24);
        thumbnail.setErrorImageResId(R.drawable.ic_baseline_sync_disabled_24);
        thumbnail.setImageUrl("https://image.tmdb.org/t/p/w500"+movie.getPosterPath(), AppRequestQueue.getInstance().getImageLoader());

        title.setText(movie.getTitle());
        releaseDate.setText((new SimpleDateFormat("dd/MM/yyyy").format(movie.getReleaseDate())));
    }
}
