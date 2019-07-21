package com.msaviczki.themovieapp.data

import com.msaviczki.themovieapp.helper.adapter.BaseListAdapter

data class MovieMap(
    override val type: Int = 0,
    var title: String,
    var realeseDate: String,
    var image: String,
    var id: Int
) : BaseListAdapter.ItemView