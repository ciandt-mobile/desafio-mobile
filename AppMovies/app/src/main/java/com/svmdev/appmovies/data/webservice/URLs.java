package com.svmdev.appmovies.data.webservice;

public class URLs {

    public static final String apiKey = "7fd2a8cbdc415a80c4dd7a989797dd4d";
    public static final String language = "pt-BR";

    public static final String genreUrl = "https://api.themoviedb.org/3/genre/movie/list?api_key="+apiKey+"&language=pt-BR";
    public static final String imageUrl = "https://image.tmdb.org/t/p/original";

    public static final String upcommingUrl = "https://api.themoviedb.org/3/movie/upcoming";
    public static final String popularUrl = "https://api.themoviedb.org/3/movie/popular";

    public static final String detailUrl = "https://api.themoviedb.org/3/movie/";
    public static final String castUrl = "https://api.themoviedb.org/3/movie/"; // movie_id/credits



}