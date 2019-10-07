package com.tartagliaeg.tmdb.tools_android

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso

class ImageTools (private val context: Context){
    private val picasso: Picasso = Picasso.with(context)

    fun loadImageFromUrl(url: String, target: AppCompatImageView) {
        picasso.load(url).into(target)
    }
}