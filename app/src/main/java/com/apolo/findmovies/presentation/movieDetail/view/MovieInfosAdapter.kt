package com.apolo.findmovies.presentation.movieDetail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolo.findmovies.R
import com.apolo.findmovies.data.model.MovieInfoViewModel
import kotlinx.android.synthetic.main.view_holder_movie_detail.view.*

class MovieInfosAdapter(private val movieInfoViewModel: List<MovieInfoViewModel>) : RecyclerView.Adapter<MovieInfosAdapter.MovieInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_detail, null, false))

    override fun getItemCount() = movieInfoViewModel.size

    override fun onBindViewHolder(movieHolder: MovieInfoViewHolder, position: Int) {
        movieHolder.bind(movieInfoViewModel[position])
    }

    inner class MovieInfoViewHolder(private val viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        fun bind(movieInfo : MovieInfoViewModel) {

            viewHolder.apply {
//                this.bannerImage TODO
                this.actor_name.text = movieInfo.actorName
                this.role.text = movieInfo.roleName
            }

        }
    }
}