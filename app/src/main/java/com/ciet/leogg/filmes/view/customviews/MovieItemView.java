package com.ciet.leogg.filmes.view.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.model.Movie;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieItemView extends CardView{
    private MyNetworkImageView thumbnail;
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

        thumbnail = findViewById(R.id.movie_thumbnail);
        releaseDate = findViewById(R.id.movie_release_date);
        title = findViewById(R.id.movie_title);
    }

    public void updateMovie(Movie movie){
        thumbnail.setDefaultImageResId(R.drawable.ic_sync_portrait);
        thumbnail.setErrorImageResId(R.drawable.ic_sync_disabled_portrait);
        String path = movie.getPosterPath() == null
                ?"https://via.placeholder.com/600x900/bcaaa4/bcaaa4.png"
                :"https://image.tmdb.org/t/p/w500"+movie.getPosterPath();
        thumbnail.setImageUrl(path, AppRequestQueue.getInstance().getImageLoader());

        title.setText(movie.getTitle());
        releaseDate.setText((new SimpleDateFormat("dd/MM/yyyy")
                .format(movie.getReleaseDate() == null ? new Date(1400163380494L) : movie.getReleaseDate())));
    }
}
