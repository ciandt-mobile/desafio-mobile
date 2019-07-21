package com.msaviczki.themovieapp.helper.extension

import android.app.Activity
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.msaviczki.themovieapp.network.core.ApiConstants

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit): LiveData<T> =
    liveData.apply { observe(this@observe, Observer { observable -> observable?.let { action(it) } }) }

fun String?.letIfNotNullOrEmpty(call: () -> Unit) {
    if (this.isNullOrEmpty().not()) {
        call.invoke()
    }
}

fun String.letIfNotEmpty(call: () -> Unit) {
    if (this.isNotEmpty()) {
        call.invoke()
    }
}

infix fun ViewGroup.inflate(layoutResId: Int): View =
    LayoutInflater.from(context).inflate(layoutResId, this, false)

fun ImageView.laodUrlImage(query: String) {
    val url = ApiConstants.IMAGE_URL + query
    Glide.with(this)
        .load(url)
        .into(this)
}

fun Activity.orientationState(portraitCall: () -> Unit, landScapeCall: () -> Unit) {
    val orientation = resources.configuration.orientation
    if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        landScapeCall.invoke()
    } else {
        portraitCall.invoke()
    }
}