package br.com.josericardooliveira.desafiomobile.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.josericardooliveira.desafiomobile.R
import br.com.josericardooliveira.desafiomobile.databinding.MovieListFragmentBinding
import kotlinx.android.synthetic.main.movie_list_fragment.*


class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel

    private lateinit var binding: MovieListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        val adapter = MovieListAdapter(
            viewModel.upcomingMoviesList.value ?: listOf()
        )
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = GridLayoutManager(context, 2)

        viewModel.popularMoviesList.observe(this, Observer {
            movieInfoList -> if(viewModel.isUpcoming.value == false) adapter.setData(movieInfoList)
        })
        viewModel.upcomingMoviesList.observe(this, Observer {
                movieInfoList -> if(viewModel.isUpcoming.value == true)adapter.setData(movieInfoList)
        })

        binding.viewModel = viewModel
    }

}
