package br.com.suelen.mobilechallenge.movies.upcoming

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import br.com.suelen.mobilechallenge.databinding.FragmentMovieListBinding
import br.com.suelen.mobilechallenge.movies.PaginationScrollListener
import br.com.suelen.mobilechallenge.utilities.EspressoIdlingResource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UpcomingFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<UpcomingViewModel> { viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieListBinding.inflate(inflater, container, false)

        val adapter = UpcomingMovieAdapter()
        binding.movieList.adapter = adapter

        val paginationScrollListener = object : PaginationScrollListener(binding.movieList.layoutManager!!) {
            override fun loadMoreItems() {
                viewModel.loadMoreMovies()
            }
        }

        binding.movieList.addOnScrollListener(paginationScrollListener)

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.movieList.visibility = View.GONE
                binding.errorMessage.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.movieList.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            binding.movieList.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.errorMessage.visibility = View.VISIBLE
            binding.errorMessage.text = it
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel.getUpcomingMovies()
    }
}