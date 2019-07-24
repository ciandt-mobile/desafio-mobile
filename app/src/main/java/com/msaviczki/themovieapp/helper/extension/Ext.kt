package com.msaviczki.themovieapp.helper.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.msaviczki.themovieapp.network.core.ApiConstants

inline fun <reified T : Any> AppCompatActivity.start(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = intent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> intent(context: Context): Intent = Intent(context, T::class.java)

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

fun ImageView.loadUrlImage(query: String) {
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