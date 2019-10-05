package com.brunodiegom.movies.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.brunodiegom.movies.R
import com.brunodiegom.movies.databinding.ActivityMainBinding
import com.brunodiegom.movies.model.Detail
import com.brunodiegom.movies.model.Movie
import com.brunodiegom.movies.view.adapter.MovieAdapter
import com.brunodiegom.movies.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.chip_group_filter
import kotlinx.android.synthetic.main.activity_main.movie_list
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * [AppCompatActivity] used to present the list of items.
 */
class MainActivity : AppCompatActivity(), MovieAdapter.MovieAdapterItemListener {

    private val viewModel: MainActivityViewModel by viewModel()

    private val adapter by lazy { MovieAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()
        setupFilter()
        setupListAdapter()
    }

    private fun setupListAdapter() {
        viewModel.movies.observe(this, Observer { adapter.submitList(it) })
        movie_list.adapter = adapter
    }

    private fun setupFilter() {
        viewModel.filter.observe(this, Observer {
            chip_group_filter.check(chip_group_filter.getChildAt(it).id)
        })
    }

    private fun bindViewModel() {
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    override fun onClick(movie: Movie) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(Detail.EXTRA_ID, movie.id)
        })
    }
}
