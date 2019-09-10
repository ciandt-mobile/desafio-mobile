package com.apolo.findmovies.presentation.home.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apolo.findmovies.R
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.presentation.home.viewModel.HomeViewModel
import com.apolo.findmovies.presentation.movieDetail.view.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by inject()

    private lateinit var moviesAdapter: MoviesAdapter

    private var currentPage = 1
    private var lastPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        moviesAdapter = MoviesAdapter(mutableListOf()) { openMovieDetail(it) }

        movies_list.adapter = moviesAdapter

        homeViewModel.getMoviesLiveData().observe(this, Observer { resource ->
            when(resource?.status) {
                Resource.Status.SUCCESS -> {
                    category_button.isEnabled = true
                    loader.visibility = View.GONE
                    movies_list.visibility = View.VISIBLE

                    currentPage = resource.currentPage?: 1
                    lastPage = resource.lastPage?: 1

                    resource.data?.let {moviesList ->
                        if (currentPage == 1) {
                            moviesAdapter.setMovies(moviesList)
                        } else {
                            moviesAdapter.addMovies(moviesList)
                        }
                    }
                }
                Resource.Status.LOADING -> {
                    loader.visibility = View.VISIBLE
                    category_button.isEnabled = false
                }
                else -> {
                    loader.visibility = View.GONE
                    movies_list.visibility = View.VISIBLE
                    category_button.isEnabled = true
                }
            }
        })

        category_button.setOnCheckedChangeListener { radioGroup, p1 ->
            resetPages()
            homeViewModel.onCategoryChange(radioGroup?.checkedRadioButtonId == upcoming_option.id)

            if (radioGroup?.checkedRadioButtonId == upcoming_option.id) {
                movies_list.visibility = View.GONE
                selected_option_title.text = resources.getString(R.string.selected_movie_title, getText(R.string.upcoming_option_title))
            } else {
                movies_list.visibility = View.GONE
                selected_option_title.text = resources.getString(R.string.selected_movie_title, getText(R.string.popular_option_title))
            }
        }


        movies_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, verticalPosition: Int) {
                if (loader.visibility == View.VISIBLE) {
                    return
                }

                val visibleItemCount = movies_list.layoutManager?.childCount ?: 0
                val totalItemCount = movies_list.layoutManager?.itemCount ?: 0
                val firstVisibleItemPos =  (movies_list.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

                if (firstVisibleItemPos + visibleItemCount >= totalItemCount - 4 && isNotLastPage()) {
                    homeViewModel.onReachedEndOfPage(isUpcomingMovies(), ++currentPage)
                }
            }

        })

        homeViewModel.onViewReady()
    }

    private fun resetPages() = currentPage == 1

    fun isNotLastPage() = lastPage != currentPage

    fun isUpcomingMovies() = upcoming_option.isChecked

    private fun openMovieDetail(movieViewModel: MovieViewModel) {
        startActivity(
            MovieDetailActivity.getStartIntent(this, movieViewModel)
        )
    }
}
