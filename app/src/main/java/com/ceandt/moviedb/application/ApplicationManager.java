package com.ceandt.moviedb.application;

import android.app.Application;

import com.ceandt.moviedb.injection.component.DaggerMainComponent;
import com.ceandt.moviedb.injection.component.MainComponent;

public class ApplicationManager extends Application {

    private MainComponent mMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mMainComponent = DaggerMainComponent.create();
    }

    public MainComponent getMainComponent() {
        return mMainComponent;
    }
}
