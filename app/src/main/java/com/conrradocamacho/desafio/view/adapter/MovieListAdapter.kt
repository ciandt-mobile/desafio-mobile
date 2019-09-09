package com.conrradocamacho.desafio.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.conrradocamacho.desafio.BuildConfig
import com.conrradocamacho.desafio.R
import com.conrradocamacho.desafio.network.bean.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Conrrado Camacho on 01/09/2019.
 * con.webmaster@gmail.com
 */
class MovieListAdapter(private val movieList: MutableList<Movie>, private val listener: MovieListItem):
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    interface MovieListItem {
        fun onClickItem(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view, parent.context, listener)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    fun updateList(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val context: Context, private val listener: MovieListItem): RecyclerView.ViewHolder(itemView) {

        fun onBind(movie: Movie) {
            itemView.textViewTitle.text = movie.title
            itemView.textViewDate.text = movie.releaseDate
            Glide.with(context)
                .load("${BuildConfig.BASE_IMAGE_URL}t/p/w300/${movie.posterPath}")
                .into(itemView.imageView)

            itemView.setOnClickListener {listener.onClickItem(movie)}
        }
    }
}
