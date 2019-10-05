package com.brunodiegom.movies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.brunodiegom.movies.R
import com.brunodiegom.movies.model.Movie
import com.brunodiegom.movies.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.chip_group_filter
import kotlinx.android.synthetic.main.activity_main.chip_popular
import kotlinx.android.synthetic.main.activity_main.chip_upcoming
import kotlinx.android.synthetic.main.activity_main.list_title
import kotlinx.android.synthetic.main.activity_main.movie_list
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * [AppCompatActivity] used to present the list of items.
 */
class MainActivity : AppCompatActivity(), MovieAdapter.MovieAdapterItemListener {

    private val viewModel: MainActivityViewModel by viewModel()

    private val adapter by lazy { MovieAdapter(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListAdapter()
        setupListTitle()
        setupFilter()
    }

    private fun setupListAdapter() {
        viewModel.movies.observe(this, Observer { adapter.submitList(it) })
        movie_list.adapter = adapter
    }

    private fun setupListTitle() {
        viewModel.title.observe(this, Observer { list_title.setText(it) })
    }

    private fun setupFilter() {
        viewModel.filter.observe(this, Observer {
            chip_group_filter.check(chip_group_filter.getChildAt(it).id)
        })
        chip_upcoming.setOnClickListener { viewModel.selectUpcoming() }
        chip_popular.setOnClickListener { viewModel.selectPopular() }
    }

    override fun onClick(movie: Movie) {
        // Open detail activity
    }
}
