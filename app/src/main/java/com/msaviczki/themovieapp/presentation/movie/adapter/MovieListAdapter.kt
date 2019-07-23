package com.msaviczki.themovieapp.presentation.movie.adapter

import android.view.ViewGroup
import com.msaviczki.themovieapp.R
import com.msaviczki.themovieapp.helper.adapter.BaseListAdapter
import com.msaviczki.themovieapp.helper.extension.inflate

class MovieListAdapter : BaseListAdapter() {

    override fun getItemViewHolder(parent: ViewGroup): BaseViewHolder<ItemView> =
        MovieListViewHolder(parent.inflate(R.layout.movie_item)) as BaseViewHolder<ItemView>
}