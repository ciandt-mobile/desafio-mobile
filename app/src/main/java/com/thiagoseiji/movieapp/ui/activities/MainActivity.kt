package com.thiagoseiji.movieapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.thiagoseiji.movieapp.ui.adapters.MoviesAdapter
import com.thiagoseiji.movieapp.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.thiagoseiji.movieapp.R


class MainActivity : AppCompatActivity() {

    private val moviesVM: MoviesViewModel by viewModel()

    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_recycler_view.adapter = moviesAdapter
        main_recycler_view.layoutManager = GridLayoutManager(this, 3)

        val dividerItemDecoration = DividerItemDecoration(this,
            GridLayoutManager.HORIZONTAL
        )
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_horizontal))
        val dividerItemDecoration2 = DividerItemDecoration(this,
            GridLayoutManager.VERTICAL
        )
        dividerItemDecoration2.setDrawable(resources.getDrawable(R.drawable.divider_vertical))
        main_recycler_view.addItemDecoration(dividerItemDecoration)
        main_recycler_view.addItemDecoration(dividerItemDecoration2)

        val liveData = moviesVM.getData()

        liveData.observe(this, Observer { data ->
            if (data != null) {
                moviesAdapter.updateMovieList(data.results)
            }
        })
    }
}
