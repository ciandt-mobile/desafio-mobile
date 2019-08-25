package com.svmdev.appmovies.help;

import com.svmdev.appmovies.data.model.Cast;
import com.svmdev.appmovies.data.model.Movie;

import java.util.ArrayList;

public class Variables {

    public static ArrayList<Movie> popularList;
    public static ArrayList<Movie> upcommingList;

    public static ArrayList<Integer> popularPages;
    public static ArrayList<Integer> upcommingPages;

    public static ArrayList<Cast> movieCast;
    public static int lastPagePopular;
    public static int lastPageUpcomming;

    public static void init(){
        popularList = new ArrayList<>();
        upcommingList = new ArrayList<>();
        popularPages = new ArrayList<>();
        upcommingPages = new ArrayList<>();
        movieCast = new ArrayList<>();
        lastPagePopular = -1;
        lastPageUpcomming = -1;
    }

}