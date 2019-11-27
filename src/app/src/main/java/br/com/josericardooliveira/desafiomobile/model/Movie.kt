package br.com.josericardooliveira.desafiomobile.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(
    val name: String,
    val description: String,
    val bannerUrl: String,
    val imageUrl: String,
    val gender: String,
    val debutDate: String,
    val duration: String,
    val movieCast: List<Actor>
    ) {
    companion object {
        @JvmStatic
        @BindingAdapter( "picassoLoad" )
        fun loadRemoteImage(iv: ImageView, url: String ){

            Picasso.get().load( url ).into( iv )
        }
    }

    fun formattedDate():String {
        val readFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val humanFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(debutDate, readFormat)
        return humanFormat.format(date)
    }
}