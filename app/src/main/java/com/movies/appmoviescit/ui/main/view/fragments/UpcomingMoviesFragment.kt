package com.movies.appmoviescit.ui.main.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmoviescit.R
import com.movies.appmoviescit.model.MovieDetailComplete
import com.movies.appmoviescit.model.MovieItem
import com.movies.appmoviescit.ui.main.viewModel.MoviesViewModel
import com.movies.appmoviescit.ui.movieDetail.view.MovieDetailActivity
import com.movies.appmoviescit.utils.LoadPageScrollListener
import kotlinx.android.synthetic.main.popular_movies.*
import kotlinx.android.synthetic.main.upcoming_movies.*
import kotlinx.android.synthetic.main.upcoming_movies.my_recycler_view
import java.io.Serializable

class UpcomingMoviesFragment: Fragment(),
    MoviesAdapter.MovieItemListener, Serializable, LoadPageScrollListener.LoadPageScrollLoadMoreListener {

    private val loadPageScrollListener = LoadPageScrollListener(this)

    val viewModel = MoviesViewModel()
    private lateinit var adapterUpcomingMovies: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecyclerView()
        loadMovies()
    }

    private fun setupObservers() {
        viewModel.upcomingMovies.observe(this, Observer {
            configureUpcomingMovies(it)
        })
        viewModel.error.observe(this, Observer {
            showError()
        })
        viewModel.movieDetailComplete.observe(this, Observer {
            configureDetailMovie(it)
        })
    }

    private fun configureDetailMovie(movieDetailComplete: MovieDetailComplete?) {
        movieDetailComplete?.let {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("MOVIE_DETAIL", movieDetailComplete)

            context?.let {
                it.startActivity(intent)
            }
        }
    }

    private fun configureUpcomingMovies(movies: List<MovieItem>?) {
        movies?.let {
            adapterUpcomingMovies.setContentList(it)
        }
    }

    private fun showError() {
        Toast.makeText(context, "Error searching movies", Toast.LENGTH_SHORT).show()
    }

    private fun loadMovies() {
        viewModel.loadMovies(MoviesViewModel.MOVIE_TYPE.UPCOMING)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapterUpcomingMovies =
            MoviesAdapter(context!!, this)
        adapterUpcomingMovies.setHasStableIds(true)

        my_recycler_view.layoutManager = layoutManager
        my_recycler_view.addOnScrollListener(loadPageScrollListener)
        my_recycler_view.adapter = adapterUpcomingMovies
        my_recycler_view.setBackgroundResource(android.R.color.white)
    }

    override fun getMovieItemDetail(item: MovieItem) {
        viewModel.getMovieDetail(item.id)
    }

    override fun onLoadMore(currentPage: Int, totalItemCount: Long, recyclerView: RecyclerView) {
        viewModel.loadMovies(MoviesViewModel.MOVIE_TYPE.UPCOMING, currentPage)
    }

}