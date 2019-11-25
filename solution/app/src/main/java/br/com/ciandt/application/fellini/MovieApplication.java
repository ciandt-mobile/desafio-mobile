package br.com.ciandt.application.fellini;

import android.app.Application;

import com.squareup.picasso.Picasso;

import br.com.ciandt.application.fellini.service.legacycode.Client;
import br.com.ciandt.application.fellini.service.legacycode.CrewService;
import br.com.ciandt.application.fellini.service.legacycode.GenresService;
import br.com.ciandt.application.fellini.service.legacycode.MovieService;
import br.com.ciandt.application.fellini.service.legacycode.SearchService;

public class MovieApplication extends Application {

    private static final String TAG = "movieapplication";

    private static MovieApplication instance = null;

    public static MovieApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        /* SquareUp Picasso Lib Singleton Instance */
        Picasso.setSingletonInstance(new Picasso.Builder(this).build());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
