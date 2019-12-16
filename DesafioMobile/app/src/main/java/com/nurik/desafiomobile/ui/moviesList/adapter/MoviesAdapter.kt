package com.nurik.desafiomobile.ui.moviesList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nurik.desafiomobile.R
import com.nurik.desafiomobile.databinding.MovieItemListBinding
import com.nurik.desafiomobile.pojo.Movie
import com.nurik.desafiomobile.utils.ItemClickListener

class MoviesAdapter (private val movies: List<Movie>,
                     private val listener: ItemClickListener): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
            MoviesViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.movie_item_list,
                    parent, false
            ))

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.recyclerViewBinding.movie = movies[position]
        holder.recyclerViewBinding.root.setOnClickListener{
            listener.onItemClickListener(movie)
        }
    }

    override fun getItemCount() = movies.size

    inner class MoviesViewHolder(val recyclerViewBinding: MovieItemListBinding)
        : RecyclerView.ViewHolder(recyclerViewBinding.root)
}
