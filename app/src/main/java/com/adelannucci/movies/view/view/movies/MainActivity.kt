package com.adelannucci.movies.view.view.movies

import android.databinding.DataBindingUtil
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.adelannucci.movies.R
import com.adelannucci.movies.data.ApiTheMovieService
import com.adelannucci.movies.data.MoviesRepository
import com.adelannucci.movies.data.remote.ConnectivityInterceptorImpl
import com.adelannucci.movies.data.remote.TheMovieDataSourceImpl
import com.adelannucci.movies.databinding.ActivityMainBinding
import com.adelannucci.movies.view.viewmodel.FilterMoviesEnum
import com.adelannucci.movies.view.viewmodel.movies.MoviesAdapter
import com.adelannucci.movies.view.viewmodel.movies.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var viewModel: MoviesViewModel? = null
    var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiTheMovieService(ConnectivityInterceptorImpl(applicationContext))
        val dataSource = TheMovieDataSourceImpl(apiService)
        val repository = MoviesRepository(dataSource)

        activityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        viewModel = MoviesViewModel(repository)
        activityMainBinding?.viewModel = viewModel
        activityMainBinding?.recyclerView?.adapter = MoviesAdapter(emptyList())
        activityMainBinding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_movies, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.upcoming -> {
                update(FilterMoviesEnum.UPCOMING)
                true
            }
            R.id.popular -> {
                update(FilterMoviesEnum.POPULAR)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun update(filter: FilterMoviesEnum) {
        viewModel?.load(filter)
        updateTitle("Movies: ${filter.value.capitalize()}")
    }

    override fun onResume() {
        super.onResume()
        update(FilterMoviesEnum.UPCOMING)
    }

    fun updateTitle(title: String) {
        supportActionBar?.let {
            setTitle(title)
        }
    }
}