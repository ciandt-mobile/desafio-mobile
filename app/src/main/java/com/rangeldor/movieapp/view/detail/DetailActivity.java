package com.rangeldor.movieapp.view.detail;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.rangeldor.movieapp.R;
import com.rangeldor.movieapp.Utils;
import com.rangeldor.movieapp.api.MovieApi;
import com.rangeldor.movieapp.api.MovieClient;
import com.rangeldor.movieapp.model.Movie;
import com.rangeldor.movieapp.view.home.HomeActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private static final String TAG = "DetailActivity";
    private static final String BASE_URL_YOUTUBE = "https://www.youtube.com/watch?v=";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.movieThumb)
    ImageView movieThumb;

   // @BindView(R.id.category)
   // TextView category;

  //  @BindView(R.id.country)
   // TextView country;

   // @BindView(R.id.instructions)
   // TextView instructions;

    //@BindView(R.id.ingredient)
    //TextView ingredients;

   // @BindView(R.id.measure)
   // TextView measures;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.youtube)
    TextView youtube;

    @BindView(R.id.source)
    TextView source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_detail );
        ButterKnife.bind ( this );

        setupActionBar ( );

        Intent intent = getIntent ();
        String movie_id = intent.getStringExtra ( HomeActivity.EXTRA_DETAIL_ID );
        DetailPresenter presenter = new DetailPresenter (this);
        presenter.getMovieById(movie_id);
    }

    private void setupActionBar() {
        setSupportActionBar ( toolbar );
        collapsingToolbarLayout.setContentScrimColor ( getResources ( ).getColor ( R.color.colorWhite ) );
        collapsingToolbarLayout.setCollapsedTitleTextColor ( getResources ( ).getColor ( R.color.colorAccent ) );
        collapsingToolbarLayout.setExpandedTitleColor ( getResources ( ).getColor ( R.color.colorWhite ) );

        if ( getSupportActionBar ( ) != null ) {
            getSupportActionBar ( ).setDisplayHomeAsUpEnabled ( true );
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener ( (appBarLayout , verticalOffset) -> {
            if ( (collapsingToolbarLayout.getHeight ( ) + verticalOffset) < (2 * ViewCompat.getMinimumHeight ( collapsingToolbarLayout )) ) {
                if ( toolbar.getNavigationIcon ( ) != null )
                    toolbar.getNavigationIcon ( ).setColorFilter ( getResources ( ).getColor ( R.color.colorAccent ) , PorterDuff.Mode.SRC_ATOP );
                favoriteItemColor.mutate ( ).setColorFilter ( getResources ( ).getColor ( R.color.colorAccent ) ,
                        PorterDuff.Mode.SRC_ATOP );

            } else {
                if ( toolbar.getNavigationIcon ( ) != null )
                    toolbar.getNavigationIcon ( ).setColorFilter ( getResources ( ).getColor ( R.color.colorWhite ) , PorterDuff.Mode.SRC_ATOP );
                favoriteItemColor.mutate ( ).setColorFilter ( getResources ( ).getColor ( R.color.colorWhite ) ,
                        PorterDuff.Mode.SRC_ATOP );
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ( ).inflate ( R.menu.menu_detail , menu );
        MenuItem favoriteItem = menu.findItem ( R.id.favorite );
        Drawable favoriteItemColor = favoriteItem.getIcon ( );
        setupColorActionBarIcon ( favoriteItemColor );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ( )) {
            case android.R.id.home:
                onBackPressed ( );
                return true;
            default:
                return super.onOptionsItemSelected ( item );
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility ( View.VISIBLE );
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility ( View.INVISIBLE );
    }

    @Override
    public void setResult(Movie.Result result) {
        String strMovieThumb = MovieClient.BASE_IMAGE_URL + result.getPosterPath();
        Picasso.get ( ).load (strMovieThumb ).into ( movieThumb );
        collapsingToolbarLayout.setTitle ( result.getTitle() );
        setupActionBar ( );

        youtube.setOnClickListener ( v -> {
            Intent intentYoutube = new Intent ( Intent.ACTION_VIEW );
            intentYoutube.setData ( Uri.parse ( BASE_URL_YOUTUBE + result.getVideos().getResults().get(0).getKey() ) );
            startActivity ( intentYoutube );
        } );

        source.setOnClickListener ( v -> {
            Intent intentSource = new Intent ( Intent.ACTION_VIEW );
            intentSource.setData ( Uri.parse ( result.getHomepage() ) );
            startActivity ( intentSource );
        } );
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage ( this , "Error" , message );
    }
}
