package com.brunodiegom.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brunodiegom.movies.R
import com.brunodiegom.movies.extension.toDate
import com.brunodiegom.movies.model.Movie
import com.brunodiegom.movies.model.Movie.Companion.DIFF_CALLBACK
import com.brunodiegom.movies.model.Movie.Companion.THUMBNAIL_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_movie.view.poster_thumbnail
import kotlinx.android.synthetic.main.adapter_movie.view.release_date
import kotlinx.android.synthetic.main.adapter_movie.view.title

/**
 * [RecyclerView.Adapter] to apply the [Movie] content on the list.
 */
class MovieAdapter(
    private val listener: MovieAdapterItemListener
) : PagedListAdapter<Movie, MovieAdapter.MovieAdapterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false))

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.view.apply {
                setOnClickListener { listener.onClick(movie) }
                title.text = movie.title
                release_date.text = movie.releaseDate.toDate()
                Picasso.get()
                    .load("$THUMBNAIL_BASE_URL${movie.posterUrl}")
                    .fit()
                    .placeholder(R.mipmap.poster_placeholder)
                    .error(R.mipmap.poster_placeholder)
                    .into(poster_thumbnail)
            }
        }
    }

    /**
     * [RecyclerView.ViewHolder] to contain the adapter view.
     */
    class MovieAdapterViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    /**
     * Item selection interface.
     */
    interface MovieAdapterItemListener {
        /**
         * How implements this method will know when an [Movie] was selected, and which one,
         */
        fun onClick(movie: Movie)
    }
}
