package br.com.josericardooliveira.desafiomobile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.josericardooliveira.desafiomobile.R
import br.com.josericardooliveira.desafiomobile.bind.BindableAdapter
import br.com.josericardooliveira.desafiomobile.databinding.GridItemLayoutBinding
import br.com.josericardooliveira.desafiomobile.model.MovieInfo

class MovieListAdapter(var items: List<MovieInfo>)
    : PagedListAdapter<MovieInfo, MovieItemViewHolder>(
    MovieInfoDiffCallback()
), BindableAdapter<List<MovieInfo>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.grid_item_layout,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val binding = holder.binding
        binding?.movie = items[position]
        binding?.executePendingBindings()
    }

    override fun setData(data: List<MovieInfo>) {
        items = data
        notifyDataSetChanged()
    }
}

class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: GridItemLayoutBinding? = DataBindingUtil.bind(itemView)
}
class MovieInfoDiffCallback : DiffUtil.ItemCallback<MovieInfo>() {

    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}