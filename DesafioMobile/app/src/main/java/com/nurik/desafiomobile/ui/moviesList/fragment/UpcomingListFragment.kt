package com.nurik.desafiomobile.ui.moviesList.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.nurik.desafiomobile.R
import com.nurik.desafiomobile.data.MoviesApi
import com.nurik.desafiomobile.data.MoviesRepository
import com.nurik.desafiomobile.ui.moviesList.MainActivity
import com.nurik.desafiomobile.ui.moviesList.MovieListViewModel
import com.nurik.desafiomobile.ui.moviesList.MovieListViewModelFactory
import com.nurik.desafiomobile.ui.moviesList.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.upcoming_fragment.*

class UpcomingListFragment : Fragment() {

    private lateinit var factoryList: MovieListViewModelFactory
    private lateinit var mListViewModel: MovieListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.upcoming_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        initFactory()
        mListViewModel = ViewModelProviders.of(this, factoryList).get(MovieListViewModel::class.java)
        mListViewModel.upcomingMovies.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view.also {
                it.setHasFixedSize(true)
                it.layoutManager = GridLayoutManager(context, 3)
                it.adapter = MoviesAdapter(movies, activity as MainActivity)
            }
        })
        mListViewModel.getUpcomingMovies()
    }

    private fun initFactory() {
        val api = MoviesApi()
        val repository = MoviesRepository(api)
        factoryList = MovieListViewModelFactory(repository)
    }
}
