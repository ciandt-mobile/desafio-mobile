package com.movies.appmoviescit.ui.main.view.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.appmoviescit.R
import com.movies.appmoviescit.model.MovieItem
import com.movies.appmoviescit.utils.MoviesImageBuilder
import kotlinx.android.synthetic.main.movie_list_item.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MoviesAdapter(val context: Context, val listener: MovieItemListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var movieContentList: List<MovieItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieItemViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.movie_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieContentList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> holder.bind(position, this.movieContentList, context, listener)
        }
    }

    fun setContentList(movieList: List<MovieItem>){
        this.movieContentList = movieList
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface MovieItemListener {
        fun getMovieItemDetail(item: MovieItem)
    }
}

class MovieItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val movieImageUrlBuilder = MoviesImageBuilder()
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    fun bind(position: Int, movieContentList: List<MovieItem>?, context: Context, listener: MoviesAdapter.MovieItemListener) {
        movieContentList?.let {
            val item = it.get(position)
            view.movie_name.text = item.title

            val date = LocalDate.parse(item.release_date)
            view.movie_date.text = context.getString(R.string.release, date.format(formatter))

            Glide.with(view)
                .load(item.poster_path?.let { src ->
                    movieImageUrlBuilder.buildPosterUrl(src)
                })
                .apply(RequestOptions().placeholder(R.drawable.ic_placeholder))
                .into(view.movie_image)
            view.movie_image.visibility = View.VISIBLE

            view.main_item_view.setOnClickListener {
                listener.getMovieItemDetail(item)
            }
        }
    }
}