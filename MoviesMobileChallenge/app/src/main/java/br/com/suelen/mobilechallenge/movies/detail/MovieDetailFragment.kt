package br.com.suelen.mobilechallenge.movies.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import br.com.suelen.mobilechallenge.R
import br.com.suelen.mobilechallenge.databinding.FragmentMovieDetailBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val movieViewModel by viewModels<MovieDetailViewModel> { viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        val binding = FragmentMovieDetailBinding.bind(view).apply {
            viewModel = movieViewModel
            lifecycleOwner = viewLifecycleOwner

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        movieViewModel.setup(args.movieId)

        val adapter = CastAdapter()
        binding.castRecyclerView.adapter = adapter
        movieViewModel.castList.observe(viewLifecycleOwner) { cast ->
            adapter.submitList(cast)
        }

        movieViewModel.isLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.movieDetailScrollview.visibility = View.GONE
                binding.errorLayout.visibility = View.GONE
                binding.progressLayout.visibility = View.VISIBLE
            } else {
                binding.movieDetailScrollview.visibility = View.VISIBLE
                binding.progressLayout.visibility = View.GONE
            }
        }

        movieViewModel.error.observe(viewLifecycleOwner) {
            binding.movieDetailScrollview.visibility = View.GONE
            binding.progressLayout.visibility = View.GONE
            binding.errorLayout.visibility = View.VISIBLE
            binding.errorMessage.text = it
        }

        movieViewModel.castError.observe(viewLifecycleOwner) {
            binding.castRecyclerView.visibility = View.GONE
        }

        return binding.root
    }
}