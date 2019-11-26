package com.adelannucci.movies

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import com.adelannucci.movies.data.ApiTheMovieService
import com.adelannucci.movies.data.MoviesRepository
import com.adelannucci.movies.data.remote.ConnectivityInterceptorImpl
import com.adelannucci.movies.data.remote.TheMovieDataSourceImpl
import com.adelannucci.movies.view.view.MoviesFragment
import com.adelannucci.movies.view.view.addFragmentTo
import com.adelannucci.movies.view.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        createFragment()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        createFragment()
    }

    private fun createViewModel(): MoviesViewModel {
        val apiService = ApiTheMovieService(ConnectivityInterceptorImpl(applicationContext))
        val dataSource = TheMovieDataSourceImpl(apiService)
        val repository = MoviesRepository(dataSource)
        return MoviesViewModel(repository, applicationContext)
    }

    private fun createFragment() {
        val fragment = MoviesFragment.newInstance(createViewModel())
        addFragmentTo(R.id.content_frame, fragment)
    }

    fun updateTitle(title: String) {
        supportActionBar?.let{
            setTitle(title)
        }
    }
}