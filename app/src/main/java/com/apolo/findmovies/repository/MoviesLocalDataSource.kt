package com.apolo.findmovies.repository

import android.content.Context
import com.apolo.findmovies.base.AppAplication
import com.apolo.findmovies.repository.model.GenresResponse
import com.google.gson.Gson

class MoviesLocalDataSource {

    companion object {
        private const val PREFERENCES_MOVIES = "PREFERENCES_MOVIES"
        private const val MOVIES_GENRES = "MOVIES_GENRES"
    }

    private val sharedPreferences = AppAplication.context?.getSharedPreferences(PREFERENCES_MOVIES, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveGenres(genres : GenresResponse) {
        sharedPreferences?.edit()?.let {
            it.putString(MOVIES_GENRES, gson.toJson(genres))
            it.apply()
        }
    }

    fun getGenres() : GenresResponse? {
        val genres = sharedPreferences?.getString(MOVIES_GENRES, null)
        return gson.fromJson(genres, GenresResponse::class.java)
    }

    fun hasGenres() = getGenres()?.genres?.isNotEmpty() ?: false
}