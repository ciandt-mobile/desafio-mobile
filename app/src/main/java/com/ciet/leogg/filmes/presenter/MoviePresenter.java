package com.ciet.leogg.filmes.presenter;

import com.android.volley.Response;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.api.JacksonListRequest;
import com.ciet.leogg.filmes.model.Cast;
import com.ciet.leogg.filmes.model.Genre;
import com.ciet.leogg.filmes.model.Movie;

import java.util.List;

public class MoviePresenter implements MoviesContract.MovieSelection {
    private MoviesContract.DetailsView detailsView;
    private List<Genre> genres;

    public MoviePresenter(MoviesContract.DetailsView detailsView) {
        this.detailsView = detailsView;
        Response.Listener<List<Genre>>listener = new Response.Listener<List<Genre>>() {
            @Override
            public void onResponse(List<Genre> response) {
                genres = response;
            }
        };
        AppRequestQueue.getInstance().addToRequestQueue(new JacksonListRequest<>("https://api.themoviedb.org/3/genre/movie/list?api_key=adda3de7d4f28a11095c260028a9a7ac&language=en-US",listener,Genre.class));
    }

    @Override
    public void loadMovie(final Movie movie) {
        Response.Listener<List<Cast>>listener = new Response.Listener<List<Cast>>() {
            @Override
            public void onResponse(List<Cast> response) {
                String genresList = "";
                for(int movieGenreId:movie.getGenreIds()){
                    for(Genre genre:genres){
                        if(genre.getId() == movieGenreId){
                            genresList+=" "+genre.getName();
                        }
                    }
                }
                detailsView.showMovie(movie,response,genresList);
            }
        };
        AppRequestQueue.getInstance().addToRequestQueue(new JacksonListRequest<>("https://api.themoviedb.org/3/movie/"+movie.getId()+"/credits?api_key=adda3de7d4f28a11095c260028a9a7ac",listener,Cast.class));
    }
}
