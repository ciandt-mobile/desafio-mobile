package com.ciet.leogg.filmes;

import android.app.Application;
import com.ciet.leogg.filmes.repository.MainRepository;

public class App extends Application {
    private static App app;
    public static App instance(){
        return app;
    }

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();
        MainRepository.getInstance().refreshMovieList();
    }
}
