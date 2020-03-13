package com.ceandt.moviedb.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String PREFERENCE_NAME = "movie_db";
    private static final String MOVIE_PREFERENCE = "moviePreference";
    public static final String POPULAR_VALUE = "popular";
    public static final String UPCOMING_VALUE = "upcoming";

    private Preferences() {
    }

    public static SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static String getMoviePreference(final Context context) {
        return getSharedPreferences(context).getString(MOVIE_PREFERENCE, POPULAR_VALUE);
    }

    public static void putMoviePreference(final Context context, final String type) {
        if (!type.equals(getMoviePreference(context))) {
            putString(context, MOVIE_PREFERENCE, type);
        }
    }

    public static void putString(final Context context, final String key, final String value) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }
}
