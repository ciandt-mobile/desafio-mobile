package com.apolo.findmovies.presentation.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolo.findmovies.R
import com.apolo.findmovies.data.model.MovieViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MoviesAdapter(private var movies : MutableList<MovieViewModel>, private val onMovieClickListener: (MovieViewModel) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

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

                Picasso.with(viewHolder.context).load(movie.previousImage).into(movie_image, object : Callback {
                    override fun onError() {
                        movie_image.setImageResource(R.drawable.movie_error_state)
                    }

                    override fun onSuccess() {
                        loader.visibility = View.GONE
                    }

                })

                setOnClickListener {
                    onMovieClickListener(movie)
                }
            }
        }
    }

    fun setMovies(movies : List<MovieViewModel>) {
        this.movies = movies as MutableList
        notifyDataSetChanged()
    }

    fun addMovies(movies : List<MovieViewModel>) {
        this.movies.addAll(itemCount, movies as MutableList)
        notifyItemRangeChanged(itemCount, itemCount + movies.size)
    }
}