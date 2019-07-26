package com.thiagoseiji.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiagoseiji.movieapp.R
import com.thiagoseiji.movieapp.data.Cast

import com.thiagoseiji.movieapp.data.Movie
import com.thiagoseiji.movieapp.ui.listeners.MovieListListener
import kotlinx.android.synthetic.main.item_cast.view.*
import kotlinx.android.synthetic.main.item_movie.view.*
import java.text.SimpleDateFormat

class CastAdapter(val data: MutableList<Cast> = mutableListOf()): RecyclerView.Adapter<CastsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastsViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CastsViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    fun updateCastList(itens: List<Cast>){
        data.clear()
        data.addAll(itens)
        notifyDataSetChanged()
    }

}

class CastsViewHolder(val view: View): RecyclerView.ViewHolder(view){

    fun bindView(item: Cast){
        with(view){

            item_cast_name.text = item.name
            item_cast_character.text = item.character

            Glide.with(view.context)
                .load("https://image.tmdb.org/t/p/w200/${item.profileImage}")
                .centerCrop()
                .into(item_cast_image)

        }
    }
}