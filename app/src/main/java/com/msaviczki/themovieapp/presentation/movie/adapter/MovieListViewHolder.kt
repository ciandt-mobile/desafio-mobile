package com.msaviczki.themovieapp.presentation.movie.adapter

import android.view.View
import com.msaviczki.themovieapp.data.MovieMap
import com.msaviczki.themovieapp.helper.adapter.BaseListAdapter
import com.msaviczki.themovieapp.helper.extension.laodUrlImage
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListViewHolder(itemView: View) : BaseListAdapter.BaseViewHolder<MovieMap>(itemView) {

    override fun bind(item: MovieMap) {
        with(itemView) {
            img_movie.laodUrlImage(item.image)
            txt_date.text = item.realeseDate
            txt_movie_name.text = item.title
        }
    }
}