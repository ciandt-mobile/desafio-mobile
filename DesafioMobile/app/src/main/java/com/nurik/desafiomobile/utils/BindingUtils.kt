package com.nurik.desafiomobile.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nurik.desafiomobile.R

@BindingAdapter("image")
fun loadImage(view: ImageView, url:String){
    val baseUrl = "http://image.tmdb.org/t/p/w185/"
    Glide.with(view).load(baseUrl+url).into(view)
}

@BindingAdapter("format")
fun formatDateString(view: TextView, value:String){
    view.text = StringUtils.convertStringDateToBRPattern(value)
}