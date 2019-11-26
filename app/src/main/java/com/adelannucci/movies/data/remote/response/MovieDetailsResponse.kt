package com.adelannucci.movies.data.remote.response

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val runtime: Int,
    val video: Boolean,
    @SerializedName(value= "poster_path")
    val posterPath: String,
    @SerializedName(value= "id")
    val remoteId: Long,
    @SerializedName(value= "backdrop_path")
    val backdropPath: String,
    @SerializedName(value= "original_title")
    val original_title: String,
    @SerializedName(value= "genre_ids")
    val genres: List<Any>,
    val title: String,
    val overview: String,
    @SerializedName(value= "release_date")
    val releaseDate: String
) {

//    companion object {
//        @BindingAdapter("posterImage")
//        @JvmStatic
//        fun loadImage(view: ImageView, imageUrl: String) {
//            Glide.with(view.getContext())
//                .load(imageUrl).apply(RequestOptions().centerCrop())
//                .into(view)
//        }
//    }
}