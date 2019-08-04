package br.com.architerure.movies.app.detail

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.architerure.movies.R
import br.com.architerure.movies.api.model.Genre
import br.com.architerure.movies.api.model.Movie
import br.com.architerure.movies.app.viewModel.MoviesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movies_datails.*

class MoviesDatailsActivity : AppCompatActivity() {

    private lateinit var genreList: List<Genre>

    val movie: Movie?
        get() = intent.getSerializableExtra("MoviesObject") as Movie

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_movies_datails)

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        moviesViewModel.genreList()
        movie?.let {
            moviesViewModel.castList(it.id)
        }

        moviesViewModel.allGenres.observe(this, Observer {
            it.let {genresMove ->
                genresMove.let {

                    genreList = it.genres

                    var genreString = ""
                    movie?.genre_ids?.forEach {idGenre ->
                        val genre =  genreList.first {
                            it.id == idGenre
                        }

                        genreString += " ${genre.name}"
                    }
                    text_detail_description.text = genreString.trim()
                }
            }
        })

        moviesViewModel.allCast.observe(this, Observer {
            it.let {castMovie ->
                castMovie.let {
                    if (castMovie.cast.size > 0)
                    Picasso.get()
                        .load(Uri.parse("http://image.tmdb.org/t/p/w500${castMovie.cast[0].profile_path}"))
                        .into(imageViewCast01)
                    if (castMovie.cast.size > 1)
                        Picasso.get()
                            .load(Uri.parse("http://image.tmdb.org/t/p/w500${castMovie.cast[1].profile_path}"))
                            .into(imageViewCast02)
                    if (castMovie.cast.size > 2)
                        Picasso.get()
                            .load(Uri.parse("http://image.tmdb.org/t/p/w500${castMovie.cast[2].profile_path}"))
                            .into(imageViewCast03)
                }
            }
        })

        text_detail_title.text = movie?.title
        text_detail_comment.text = movie?.overview
        text_detail_year.text = movie?.release_date?.substring(0,4)

        Picasso.get()
            .load(Uri.parse("http://image.tmdb.org/t/p/w500${movie?.backdrop_path}"))
            .into(imageViewPoster)





    }


}
