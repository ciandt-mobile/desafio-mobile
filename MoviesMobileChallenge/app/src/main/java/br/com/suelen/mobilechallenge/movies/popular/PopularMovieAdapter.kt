package br.com.suelen.mobilechallenge.movies.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.suelen.mobilechallenge.data.model.Movie
import br.com.suelen.mobilechallenge.databinding.MovieItemBinding
import br.com.suelen.mobilechallenge.movies.HomeFragmentDirections
import br.com.suelen.mobilechallenge.utilities.DateTimeUtil

class PopularMovieAdapter :  ListAdapter<Movie, PopularMovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    class MovieViewHolder(private val binding : MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.movie?.id?.let {
                    val direction = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it)
                    view.findNavController().navigate(direction)
                }
            }
        }

        companion object {
            fun from(parent : ViewGroup) : MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }

        fun bind(item : Movie) {
            binding.apply {
                movie = item

                val releaseDate : String? = item.release_date
                releaseDateField = releaseDate?.apply {
                    DateTimeUtil.reformatStringDate(releaseDate)
                }

                executePendingBindings()
            }
        }
    }

}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}