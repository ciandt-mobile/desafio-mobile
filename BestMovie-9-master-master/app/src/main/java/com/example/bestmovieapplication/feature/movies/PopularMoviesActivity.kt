package com.example.bestmovieapplication.feature.movies

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovieapplication.R
import com.example.bestmovieapplication.api.repository.Apifactory
import com.example.bestmovieapplication.api.repository.model.Movie
import com.example.bestmovieapplication.api.repository.repository.MovieRepository
import com.example.bestmovieapplication.feature.movies.adapter.MoviesAdapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class PopularMoviesActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieList: MutableList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movieViewModel.repository = MovieRepository(Apifactory.tmdbApi)

        movieViewModel.fetchMovies()

        movieViewModel.popularMoviesLiveData.observe(this, Observer {
            if (it != null) {
                movieList = it
                configureRecyclerView(movieList)
            } else {
                Toasty.error(this@PopularMoviesActivity, "Ocorreu um problema ao receber a lista de filmes.")
            }
        })

        popular_movies_cv.setOnClickListener {
            popular_movies_cv.setCardBackgroundColor(Color.parseColor("#ffcb05"))
            upcoming_movies_cv.setCardBackgroundColor(Color.BLACK)
            movieViewModel.fetchMovies()
            header_text_view.text = getString(R.string.popular_movies_label)
        }

        upcoming_movies_cv.setOnClickListener {
            popular_movies_cv.setCardBackgroundColor(Color.BLACK)
            upcoming_movies_cv.setCardBackgroundColor(Color.parseColor("#ffcb05"))
            configureRecyclerView(upcomingMovies())
            header_text_view.text = getString(R.string.upcoming_movies_label)
        }
    }

    private fun configureRecyclerView(mutableList: MutableList<Movie>){

        val recyclerView = recycler_view_movies
        recyclerView.adapter = MoviesAdapter(mutableList, this)

        val layoutManager = GridLayoutManager(this@PopularMoviesActivity, 3 )
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnItemClickListener(object :
            OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val movieId =  movieList[position].id
                val intent = Intent(this@PopularMoviesActivity,
                    DetailsActivity::class.java)
                intent.putExtra("movieId", movieId)
                startActivity(intent)
            }
        })

    }

    private fun upcomingMovies(): MutableList<Movie> {
        return movieViewModel.latestMovies(movieList)
    }


    private fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }

        })
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }
}
