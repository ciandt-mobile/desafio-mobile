package com.brunodiegom.movies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.brunodiegom.movies.R
import com.brunodiegom.movies.model.Movie
import com.brunodiegom.movies.viewmodel.MainActivityViewModel
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
        setContentView(R.layout.activity_main)

        viewModel.movies.observe(this, Observer { adapter.submitList(it) })
        movie_list.adapter = adapter
    }

    override fun onClick(movie: Movie) {
        // Open detail activity
    }
}
