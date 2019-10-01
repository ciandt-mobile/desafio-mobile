package com.pereira.tiago.desafio.mobile.singletons;

import com.pereira.tiago.desafio.mobile.databasemodels.DaoSession;
import com.pereira.tiago.desafio.mobile.databasemodels.Movie;

public class SingletonMovie {

    private static DaoSession mDaoSession;
    private static SingletonMovie instance;

    public static SingletonMovie getInstance() throws Exception {
        if (instance != null) {
            return instance;
        }
        throw new Exception(SingletonMovie.class.getSimpleName() + " has not been initialized.");
    }

    public static void initialize(DaoSession daoSession) throws Exception {
        instance = new SingletonMovie();
        mDaoSession = daoSession;
    }

    public boolean saveMovie(Movie movie){
        return mDaoSession.getMovieDao().insertOrReplace(movie) > 0;
    }
}
