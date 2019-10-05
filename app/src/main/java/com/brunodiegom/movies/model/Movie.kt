package com.brunodiegom.movies.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

/**
 * Data model returned from server.
 */
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String
) {
    companion object {
        const val THUMBNAIL_BASE_URL = "https://image.tmdb.org/t/p/w185"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
