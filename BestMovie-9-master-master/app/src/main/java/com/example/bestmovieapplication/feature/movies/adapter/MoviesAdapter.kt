package com.example.bestmovieapplication.feature.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovieapplication.Constants.Companion.MOVIE_BASE_URL
import com.example.bestmovieapplication.R
import com.example.bestmovieapplication.api.repository.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(private val movies: MutableList<Movie>, private val context: Context) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindView(movie)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: Movie) {

            val releaseData = itemView.movie_release_data
            val imageDescription = itemView.movie_image
            val movieName = itemView.movie_name

            releaseData.text = movie.release_date
            movieName.text  = movie.title

            Picasso.get().load(MOVIE_BASE_URL + movie.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageDescription)
        }

    }

}
