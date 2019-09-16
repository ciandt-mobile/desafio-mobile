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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.rangeldor.movieapp.adapter.RecyclerViewDetailAdapter;
import com.rangeldor.movieapp.adapter.RecyclerViewHomeAdapter;
import com.rangeldor.movieapp.api.MovieApi;
import com.rangeldor.movieapp.api.MovieClient;
import com.rangeldor.movieapp.model.Movie;
import com.rangeldor.movieapp.view.home.HomeActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindString;
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

    @BindView(R.id.textGenre)
    TextView textGenre;

    @BindView(R.id.debut_year)
    TextView debut_year;

    @BindView(R.id.duration)
    TextView duration;

    @BindView(R.id.overview)
    TextView overview;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.youtube)
    TextView youtube;

    @BindView(R.id.source)
    TextView source;

    @BindView(R.id.recyclerDetailCast)
    RecyclerView recyclerDetailCast;

    @BindString( R.string.language )
    String LANGUAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_detail );
        ButterKnife.bind ( this );

        setupActionBar ( );

        Intent intent = getIntent ( );
        String movie_id = intent.getStringExtra ( HomeActivity.EXTRA_DETAIL_ID );
        DetailPresenter presenter = new DetailPresenter ( this );
        presenter.getMovieById ( movie_id, "1", LANGUAGE );
        presenter.getMovieCastsById ( movie_id, "1", LANGUAGE );
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
        String strMovieThumb = MovieClient.BASE_IMAGE_URL + result.getPosterPath ( );
        Picasso.get ( ).load ( strMovieThumb ).into ( movieThumb );
        collapsingToolbarLayout.setTitle ( result.getTitle ( ) );

        StringBuilder name_genre = new StringBuilder ( );
        StringBuilder runtime = new StringBuilder ( );

        for (Movie.Result.Genre genre : result.getGenres ( )) {
            if ( result.getGenres ( ).indexOf ( genre ) == result.getGenres ( ).size ( ) - 1 ) {
                name_genre.append ( genre.getName ( ) );
                break;
            }

            name_genre.append ( genre.getName ( ) );
            name_genre.append ( " / " );
        }

        long minutes = Integer.parseInt ( result.getRuntime () )  % 60;
        long hours = (Integer.parseInt ( result.getRuntime () ) - minutes) / 60;
        runtime.append ( hours ).append ( "h " ).append ( minutes ).append ( "m" );

        textGenre.setText ( name_genre.toString ( ) );
        debut_year.setText ( getYearToDate ( result.getReleaseDate () ) );
        duration.setText ( runtime.toString () );
        overview.setText ( result.getOverview () );

        setupActionBar ( );

        youtube.setOnClickListener ( v -> {
            Intent intentYoutube = new Intent ( Intent.ACTION_VIEW );
            intentYoutube.setData ( Uri.parse ( BASE_URL_YOUTUBE + result.getVideos ( ).getResults ( ).get ( 0 ).getKey ( ) ) );
            startActivity ( intentYoutube );
        } );

        source.setOnClickListener ( v -> {
            Intent intentSource = new Intent ( Intent.ACTION_VIEW );
            intentSource.setData ( Uri.parse ( result.getHomepage ( ) ) );
            startActivity ( intentSource );
        } );
    }

    @Override
    public void setDetail(Movie.Detail detail) {
        RecyclerViewDetailAdapter detailAdapter = new RecyclerViewDetailAdapter ( detail.getCast (), this );
        recyclerDetailCast.setAdapter(detailAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.HORIZONTAL, false);
        recyclerDetailCast.setLayoutManager(layoutManager);
        detailAdapter.notifyDataSetChanged ();
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage ( this , "Error" , message );
    }

    private String getYearToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataEntrada = null;
        try {
            dataEntrada = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy").format(dataEntrada);
    }
}
