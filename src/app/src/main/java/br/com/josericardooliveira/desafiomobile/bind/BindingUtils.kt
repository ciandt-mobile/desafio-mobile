package br.com.josericardooliveira.desafiomobile.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

const val ImageUrlBaseUrl: String = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
    print("teste")
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}

@BindingAdapter("picassoLoad")
fun loadRemoteImage(iv: ImageView, imagePath: String?) {
    if (imagePath != null) {
        Picasso
            .get()
            .load(ImageUrlBaseUrl + imagePath)
            .into(iv)
    }
}