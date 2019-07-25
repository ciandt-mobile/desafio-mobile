package com.thiagoseiji.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiagoseiji.movieapp.R

import com.thiagoseiji.movieapp.data.Movie
import com.thiagoseiji.movieapp.ui.listeners.MovieListListener
import kotlinx.android.synthetic.main.item_movie.view.*
import java.text.SimpleDateFormat

class MoviesAdapter( val data: MutableList<Movie> = mutableListOf(), val listener: MovieListListener): RecyclerView.Adapter<MoviesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view, listener)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    fun updateMovieList(itens: List<Movie>){
        data.clear()
        data.addAll(itens)
        notifyDataSetChanged()
    }

}

class MoviesViewHolder(val view: View, val listener: MovieListListener): RecyclerView.ViewHolder(view){

    fun bindView(item: Movie){
        with(view){
            val releaseDateFormat = SimpleDateFormat("dd/MM/yy")

            item_movie_title.text = item.title
            item_movie_release_date.text = releaseDateFormat.format(item.releaseDate)

            Glide.with(view.context)
                .load("https://image.tmdb.org/t/p/w200/${item.posterImage}")
                .centerCrop()
                .into(item_movie_poster)

            setOnClickListener {
                listener.onMovieClicked(item.id)
            }
        }
    }
}