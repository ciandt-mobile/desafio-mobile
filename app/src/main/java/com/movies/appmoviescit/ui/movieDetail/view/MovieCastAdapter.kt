package com.movies.appmoviescit.ui.movieDetail.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.appmoviescit.R
import com.movies.appmoviescit.model.Cast
import com.movies.appmoviescit.ui.main.view.fragments.MovieItemViewHolder
import com.movies.appmoviescit.utils.MoviesImageBuilder
import kotlinx.android.synthetic.main.cast_item.view.*

class MovieCastAdapter(val context: Context, var contentList: List<Cast>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieCastViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.cast_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieCastViewHolder -> holder.bind(position, this.contentList, context)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    private class MovieCastViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var mainViewCast: View = view.cast_view
        var castName: TextView = view.cast_name
        var castCharacter: TextView = view.cast_character
        var castImage: ImageView = view.cast_image

        fun bind(position: Int, contentList: List<Cast>, context: Context) {
            val castItem = contentList[position]

            Glide.with(mainViewCast)
                .load(castItem.profile_path?.let { src ->
                    MoviesImageBuilder().buildPosterUrl(src)
                })
                .apply(RequestOptions().placeholder(R.drawable.ic_placeholder))
                .into(castImage)

            castName.text = castItem.name
            castCharacter.text = castItem.character
        }
    }
}