package com.adelannucci.movies.data.remote.response

import com.google.gson.annotations.SerializedName
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import android.databinding.BindingAdapter
import android.widget.ImageView


data class MovieResponse(
    val popularity : Double,
    @SerializedName(value = "vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName(value= "poster_path")
    val posterPath: String,
    @SerializedName(value= "id")
    val remoteId: Long,
    val adult: Boolean,
    @SerializedName(value= "backdrop_path")
    val backdropPath: String,
    @SerializedName(value= "original_language")
    val originalLanguage: String,
    @SerializedName(value= "original_title")
    val original_title: String,
    @SerializedName(value= "genre_ids")
    val genreIds: List<Int>,
    val title: String,
    @SerializedName(value= "vote_average")
    val voteAverage: Double,
    val overview: String,
    @SerializedName(value= "release_date")
    val releaseDate: String
) {

    companion object {
        @BindingAdapter("posterImage")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.getContext())
                .load(imageUrl).apply(RequestOptions().centerCrop())
                .into(view)
        }
    }
}