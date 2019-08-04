package br.com.architerure.movies.app.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.architerure.movies.R
import br.com.architerure.movies.app.viewModel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val movieAdapter = MovieRecyclerAdpter(this)

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        moviesViewModel.allMovies.observe(this, Observer {
            it.let {movies ->
                movies.let {
                    movieAdapter.setMovies(it.movies)
                }
            }
        })

        diplayOrientationScreen()

        recycle_move.adapter = movieAdapter

        button_left.setOnClickListener{
            moviesViewModel.upComingMovies()
            text_title.text = "Upcoming Movies"
        }

        button_right.setOnClickListener {
            moviesViewModel.popularMovies()
            text_title.text = "Popular Movies"
        }

        button_left.performClick()
    }

    private fun diplayOrientationScreen() {
        val displayModel = resources.configuration.orientation
        if (displayModel == Configuration.ORIENTATION_PORTRAIT) {
            radioGroup.visibility = View.VISIBLE
            recycle_move.layoutManager = GridLayoutManager(this, 3) as RecyclerView.LayoutManager?
        } else {
            radioGroup.visibility = View.GONE
            recycle_move.layoutManager = GridLayoutManager(this, 5) as RecyclerView.LayoutManager?
        }
    }

}
