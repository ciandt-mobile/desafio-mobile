package com.pereira.tiago.desafio.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {

    private static final String PREF_NAME = "desafio-preferences";
    private static final String PREF_STATUS = "PREF_STATUS";

    public static boolean getPrefStatus(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean(PREF_STATUS, false);
    }

    public static void setPrefStatus(Context context, boolean status) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(PREF_STATUS, status);
        editor.apply();
    }

}
