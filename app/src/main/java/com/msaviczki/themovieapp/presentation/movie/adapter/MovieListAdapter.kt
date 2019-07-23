package com.msaviczki.themovieapp.presentation.movie.adapter

import android.view.ViewGroup
import com.msaviczki.themovieapp.R
import com.msaviczki.themovieapp.helper.adapter.BaseListAdapter
import com.msaviczki.themovieapp.helper.extension.inflate

class MovieListAdapter(private val listener: MovieListViewHolder.MovieSelectListener) : BaseListAdapter() {

    override fun getItemViewHolder(parent: ViewGroup): BaseViewHolder<ItemView> =
        MovieListViewHolder(listener, parent.inflate(R.layout.movie_item)) as BaseViewHolder<ItemView>
}