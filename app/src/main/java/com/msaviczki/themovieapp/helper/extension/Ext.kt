package com.msaviczki.themovieapp.helper.extension

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

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