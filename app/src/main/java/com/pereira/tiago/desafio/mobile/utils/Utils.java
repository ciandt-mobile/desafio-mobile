package com.pereira.tiago.desafio.mobile.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {

    public static int numberOfColums(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        return Math.abs(pxToDp(width, context) / 100);
    }

    private static int pxToDp(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static String convertDate(String data){
        String[] aux = data.split("-");
        return aux[2] + "/" + aux[1] + "/" + aux[0];
    }

    public static String yearRelease(String data){
        String[] aux = data.split("-");
        return aux[0];
    }

}
