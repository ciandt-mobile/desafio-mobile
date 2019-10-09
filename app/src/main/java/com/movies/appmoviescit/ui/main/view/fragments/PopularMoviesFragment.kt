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
import java.io.Serializable

class PopularMoviesFragment: Fragment(),
    MoviesAdapter.MovieItemListener, Serializable, LoadPageScrollListener.LoadPageScrollLoadMoreListener {

    private val loadPageScrollListener = LoadPageScrollListener(this)

    val viewModel = MoviesViewModel()
    private lateinit var adapterPopularMovies: MoviesAdapter

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
        viewModel.popularMovies.observe(this, Observer {
            configurePopularMovies(it)
        })
        viewModel.error.observe(this, Observer {
            showError()
        })
        viewModel.movieDetailComplete.observe(this, Observer {
            configureMovieDetail(it)
        })
    }

    private fun configureMovieDetail(movieDetailComplete: MovieDetailComplete?) {
        movieDetailComplete?.let {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("MOVIE_DETAIL", movieDetailComplete)

            startActivity(intent)
        }
    }

    private fun showError() {
        Toast.makeText(context, "Error searching movies", Toast.LENGTH_SHORT).show()
    }

    private fun configurePopularMovies(movies: List<MovieItem>?) {
        movies?.let {
            adapterPopularMovies.setContentList(it)
        }
    }

    private fun loadMovies() {
        viewModel.loadMovies(MoviesViewModel.MOVIE_TYPE.POPULAR)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapterPopularMovies =
            MoviesAdapter(context!!, this)
        adapterPopularMovies.setHasStableIds(true)

        my_recycler_view.layoutManager = layoutManager
        my_recycler_view.addOnScrollListener(loadPageScrollListener)
        my_recycler_view.adapter = adapterPopularMovies
        my_recycler_view.setBackgroundResource(android.R.color.white)
    }

    override fun getMovieItemDetail(item: MovieItem) {
        viewModel.getMovieDetail(item.id)
    }

    override fun onLoadMore(currentPage: Int, totalItemCount: Long, recyclerView: RecyclerView) {
        viewModel.loadMovies(MoviesViewModel.MOVIE_TYPE.POPULAR, currentPage)
    }
}