package com.adelannucci.movies.view.view

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adelannucci.movies.databinding.FragmentMoviesBinding
import com.adelannucci.movies.view.viewmodel.MoviesAdapter
import com.adelannucci.movies.view.viewmodel.MoviesViewModel

class MoviesFragment : Fragment() {

    lateinit var viewModel: MoviesViewModel

    companion object {
        fun newInstance(viewModel: MoviesViewModel): MoviesFragment {
            val fragment = MoviesFragment()
            fragment.viewModel = viewModel
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.recyclerView.adapter = MoviesAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        return binding.root
    }
}