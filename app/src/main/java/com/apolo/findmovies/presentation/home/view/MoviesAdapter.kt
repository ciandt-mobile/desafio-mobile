package com.apolo.findmovies.presentation.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolo.findmovies.R
import com.apolo.findmovies.base.model.MovieViewModel
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MoviesAdapter(private val movies : List<MovieViewModel>, private val onMovieClickListener: (MovieViewModel) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, null, true))
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MovieViewHolder(private val viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {

        fun bind(movie: MovieViewModel) {

            viewHolder.apply {
                movie_name.text = movie.name
                release_date.text = movie.releaseDate

                setOnClickListener {
                    onMovieClickListener(movie)
                }
            }
        }
    }
}