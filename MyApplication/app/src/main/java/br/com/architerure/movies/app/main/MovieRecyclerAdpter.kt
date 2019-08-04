package br.com.architerure.movies.app.main

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.architerure.movies.R
import br.com.architerure.movies.api.model.Movie
import br.com.architerure.movies.app.DefaultViewHolderKotlin
import br.com.architerure.movies.app.detail.MoviesDatailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_item_move.view.*
import org.jetbrains.anko.startActivity

class MovieRecyclerAdpter(
    val context: Context) : RecyclerView.Adapter<DefaultViewHolderKotlin>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<Movie>()

    //http://image.tmdb.org/t/p/w500/vl0k12gYvY8g8vPxxUNKbN9pHT9.jpg
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolderKotlin {
        return DefaultViewHolderKotlin(
            inflater.inflate(R.layout.cardview_item_move, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DefaultViewHolderKotlin, position: Int) {

        val movie = items[position]

        holder.view.text_date_move.text = convertDate(movie)
        holder.view.text_name_move.text = movie.title

        loadPictures(holder, movie)

        holder.view.constraintLayoutView.setOnClickListener {
           //val intent = Intent(context, MoviesDatailsActivity::class.java)
            context.startActivity<MoviesDatailsActivity> (
                "MoviesObject" to movie
            )
        }

    }

    private fun convertDate(movie: Movie) =
        movie.release_date.substring(movie.release_date.length - 2) + "/" +
                movie.release_date.substring(5, 7) + "/" +
                movie.release_date.substring(0, 4)

    private fun loadPictures(
        holder: DefaultViewHolderKotlin,
        movie: Movie
    ) {
        Log.d("Image", movie.title)
        val url = urlChoose(movie)
        if (url.isNotEmpty()) {
            Picasso.get()
                .load(Uri.parse("http://image.tmdb.org/t/p/w500$url"))
                .into(holder.view.imageView)
        }
    }

    private fun urlChoose(movie: Movie): String = when {
        !movie.poster_path.isNullOrEmpty() -> movie.poster_path
        !movie.backdrop_path.isNullOrEmpty() -> movie.backdrop_path
        else -> ""
    }


    internal fun setMovies(movies: List<Movie>) {
        this.items = movies
        notifyDataSetChanged()
    }
}