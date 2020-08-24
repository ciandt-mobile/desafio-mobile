package com.ciet.leogg.filmes.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.android.volley.Response;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.api.JacksonListRequest;
import com.ciet.leogg.filmes.api.JacksonObjectRequest;
import com.ciet.leogg.filmes.model.Cast;
import com.ciet.leogg.filmes.model.Movie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainRepository {
    private static MainRepository instance;
    private final MutableLiveData<Integer> page = new MutableLiveData<>(1);
    //movie lists
    private final MutableLiveData<List<Movie>> popularMovieList = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> upcomingMovieList = new MutableLiveData<>();
    //movie details
    private final MutableLiveData<Movie> movie = new MediatorLiveData<>();
    private final MutableLiveData<List<Cast>> castList = new MutableLiveData<>();

    private MainRepository(){
        popularMovieList.setValue(new ArrayList<Movie>());
        upcomingMovieList.setValue(new ArrayList<Movie>());
        castList.setValue(new ArrayList<Cast>());

        popularMovieList.observeForever(new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                List<Movie> newUpcomingMovieList = upcomingMovieList.getValue();
                List<Movie> possibleNewMovies = filter(popularMovieList.getValue());
                for (Movie movie:possibleNewMovies) {
                    if(!newUpcomingMovieList.contains(movie)){
                        newUpcomingMovieList.add(movie);
                    }
                }
                upcomingMovieList.postValue(newUpcomingMovieList);
            }
        });

    }

    public static MainRepository getInstance() {
        if(instance == null){
              instance = new MainRepository();
        }
        return instance;
    }

    public void refreshMovieList(){
        Response.Listener<List<Movie>>listener = new Response.Listener<List<Movie>>() {
            @Override
            public void onResponse(List<Movie> response) {
                popularMovieList.postValue(response);
            }
        };
        AppRequestQueue.getInstance().addToRequestQueue(new JacksonListRequest<>
                ("https://api.themoviedb.org/3/discover/movie?api_key=adda3de7d4f28a11095c260028a9a7ac&" +
                        "language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page="+
                        page.getValue(),listener,Movie.class));
    }

    private List<Movie> filter(List<Movie> input){
        List<Movie> output = new ArrayList<>();
        for(Movie movie:input){
            if(movie.getReleaseDate() != null
            && movie.getReleaseDate().after(Calendar.getInstance().getTime())){
                output.add(movie);
            }
        }
        return output;
    }

    public void more(){
        page.setValue(page.getValue()+1);
        refreshMovieList();
    }

    public void less(){
        if(page.getValue() > 1){
            page.setValue(page.getValue()-1);
            refreshMovieList();
        }
    }

    public void refreshMovie(final Movie inMovie){
        final Response.Listener<Movie>movieListener = new Response.Listener<Movie>() {
            @Override
            public void onResponse(Movie response) {
                movie.postValue(response);
            }
        };
        Response.Listener<List<Cast>>castListener = new Response.Listener<List<Cast>>() {
            @Override
            public void onResponse(List<Cast> response) {
                castList.postValue(response);
                AppRequestQueue.getInstance().addToRequestQueue(new JacksonObjectRequest<>
                        ("https://api.themoviedb.org/3/movie/"+inMovie.getId()
                                +"?api_key=adda3de7d4f28a11095c260028a9a7ac&language=en-US",movieListener,Movie.class));
            }
        };
        AppRequestQueue.getInstance().addToRequestQueue(new JacksonListRequest<>
                ("https://api.themoviedb.org/3/movie/"+inMovie.getId()+"/credits?api_key=adda3de7d4f28a11095c260028a9a7ac"
                        ,castListener,Cast.class));
    }

    public LiveData<List<Movie>> getPopularMovieList() {
        return popularMovieList;
    }

    public LiveData<List<Movie>> getUpcomingMovieList() {
        return upcomingMovieList;
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<List<Cast>> getCastList() {
        return castList;
    }

    public LiveData<Integer> getPage(){
        return page;
    }

}
