package com.adelannucci.movies.view.view

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.adelannucci.movies.databinding.FragmentMoviesBinding
import com.adelannucci.movies.view.viewmodel.MoviesAdapter
import com.adelannucci.movies.view.viewmodel.MoviesViewModel
import com.adelannucci.movies.MainActivity
import com.adelannucci.movies.R
import com.adelannucci.movies.view.viewmodel.FilterMoviesEnum


class MoviesFragment : Fragment() {

    lateinit var viewModel: MoviesViewModel

    companion object {
        fun newInstance(viewModel: MoviesViewModel): MoviesFragment {
            val fragment = MoviesFragment()
            fragment.viewModel = viewModel
            return fragment
        }
    }

    fun update(filter: FilterMoviesEnum) {
        viewModel.load(filter)
        (activity as MainActivity).updateTitle("Movies: ${filter.value}")
    }

    override fun onStart() {
        super.onStart()
        update(FilterMoviesEnum.UPCOMING)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_movies, menu)
        super.onCreateOptionsMenu(menu, inflater)
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

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding: FragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)

        if (viewModel != null) {
            binding.viewModel = viewModel
            binding.recyclerView.adapter = MoviesAdapter(emptyList())
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        }
        return binding.root
    }
}