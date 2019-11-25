package com.adelannucci.movies.view.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.adelannucci.movies.data.remote.response.MovieResponse
import com.adelannucci.movies.databinding.MovieItemBinding

class MoviesAdapter(var items: List<MovieResponse>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(),
    AdapterItemsContract {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: MovieItemBinding = MovieItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun replaceItems(items: List<*>) {
        this.items = items as List<MovieResponse>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieResponse) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}