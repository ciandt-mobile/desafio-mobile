package com.rangeldor.movieapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "user.preferences";
    private final String KEY_ORIENTATION = "ORIENTATION";

    public UserPreferences(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences(FILE_NAME, 0);
        editor = preferences.edit();
    }

    public void saveOrientation(int orientation){
        editor.putString(KEY_ORIENTATION, String.valueOf(orientation));
        editor.commit();
    }

    public String restoreOrientation(){
        return preferences.getString(KEY_ORIENTATION, "");
    }

}