package com.brunodiegom.movies.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.brunodiegom.movies.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    imageUrl?.let {
        Picasso.get()
            .load(imageUrl)
            .fit().centerCrop()
            .placeholder(R.mipmap.poster_placeholder)
            .error(R.mipmap.poster_placeholder)
            .into(this)
    }
}
