package com.example.bestmovieapplication.feature.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovieapplication.Constants.Companion.MOVIE_BASE_URL
import com.example.bestmovieapplication.R
import com.example.bestmovieapplication.api.repository.model.cast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cast_item.view.*

class CastAdapter(private val casts: MutableList<cast>, private val context: Context) :
    RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cast_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return casts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cast = casts[position]
        holder.bindView(cast)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(cast: cast) {
            val castName = itemView.cast_text_view
            val castImage = itemView.cast_image_view

            castName.text = cast.name

            Picasso.get().load(MOVIE_BASE_URL + cast.profile_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(castImage)
        }

    }

}
