package com.example.movies.src.utils;

import android.util.Log;

public class Logger {

    private static final String APP_TAG = "Movies.";

    public static void log(String tag, String msg) {
        Log.d(APP_TAG+tag, msg);
    }

    public static void log(String tag, Throwable e) {
        Log.d(APP_TAG+tag, "Error", e);
    }
}
