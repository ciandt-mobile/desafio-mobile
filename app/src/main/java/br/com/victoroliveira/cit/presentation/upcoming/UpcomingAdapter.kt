package br.com.victoroliveira.cit.presentation.upcoming

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.victoroliveira.cit.BuildConfig
import br.com.victoroliveira.cit.R
import br.com.victoroliveira.cit.data.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie_presentation.view.*
import java.text.SimpleDateFormat

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>(), Filterable {
    lateinit var context: Context
    lateinit var clickUpcomingListener: OnClickUpcomingListener
    private val upcomingList: MutableList<Movie> = mutableListOf()
    private var listFiltered: MutableList<Movie> = upcomingList

    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            Glide.with(context).load(BuildConfig.API_IMAGE_POSTER + movie.poster_path)
                .into(itemView.item_movie_image)

            itemView.item_movie_description.text = movie.title
            itemView.item_movie_date.text =
                SimpleDateFormat("MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(movie.release_date))

            itemView.setOnClickListener {
                clickUpcomingListener.onClick(movie)
            }
        }
    }

    fun addUpcoming(listMovie: List<Movie>) {
        listFiltered.clear()
        listFiltered.addAll(listMovie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_presentation, parent, false)
        return UpcomingViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return listFiltered.count()
    }

    private fun getItem(position: Int): Movie {
        return listFiltered[position]
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString: String = constraint.toString()
                if (charString.length > 3) {
                    val filteredList: MutableList<Movie> = mutableListOf()
                    for (s: Movie in upcomingList) {
                        if (s.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(s)
                        }
                    }
                    listFiltered = filteredList
                } else {
                    listFiltered = upcomingList

                }
                val filterResults = FilterResults()
                filterResults.values = listFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFiltered = results!!.values as MutableList<Movie>
                notifyDataSetChanged()
            }

        }
    }

    inline fun setOnClickUpcomingListener(crossinline listener: (Movie) -> Unit) {
        this.clickUpcomingListener = object :
            OnClickUpcomingListener {
            override fun onClick(movie: Movie) = listener(movie)
        }
    }

    interface OnClickUpcomingListener {
        fun onClick(movie: Movie) = Unit
    }
}