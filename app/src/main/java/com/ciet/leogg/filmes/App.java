package com.ciet.leogg.filmes;

import android.app.Application;

public class App extends Application {
    private static App app;
    public static App instance(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
