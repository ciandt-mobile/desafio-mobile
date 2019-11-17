package br.com.victoroliveira.cit.presentation.popular

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.victoroliveira.cit.BuildConfig
import br.com.victoroliveira.cit.R
import br.com.victoroliveira.cit.data.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie_presentation.view.*
import java.text.SimpleDateFormat

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    lateinit var context: Context
    lateinit var clickPopularListener: OnClickPopularListener
    private val popularList: MutableList<Movie> = mutableListOf()

    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {

            Glide.with(context).load(BuildConfig.API_IMAGE_POSTER + movie.poster_path)
                .into(itemView.item_movie_image)

            itemView.item_movie_description.text = movie.title
            itemView.item_movie_date.text =
                SimpleDateFormat("MM/yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(movie.release_date))
            itemView.setOnClickListener {
                clickPopularListener.onClick(movie)
            }
        }
    }

    fun addPopularMovies(listMovie: List<Movie>) {
        popularList.clear()
        popularList.addAll(listMovie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_presentation, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    private fun getItem(position: Int): Movie {
        return popularList[position]
    }

    inline fun setOnClickPopularListener(crossinline listener: (Movie) -> Unit) {
        this.clickPopularListener = object :
            OnClickPopularListener {
            override fun onClick(movie: Movie) = listener(movie)
        }
    }

    interface OnClickPopularListener {
        fun onClick(movie: Movie) = Unit
    }
}