package com.conrradocamacho.desafio.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.conrradocamacho.desafio.R
import com.conrradocamacho.desafio.contract.BaseListContract
import com.conrradocamacho.desafio.network.bean.Movie
import com.conrradocamacho.desafio.presenter.BaseListPresenter
import com.conrradocamacho.desafio.utils.HidingScrollListener
import com.conrradocamacho.desafio.view.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by Conrrado Camacho on 01/09/2019.
 * con.webmaster@gmail.com
 */
class BaseListFragment: Fragment(), BaseListContract.View, MovieListAdapter.MovieListItem {

    private lateinit var adapter: MovieListAdapter
    private lateinit var presenter: BaseListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        adapter = MovieListAdapter(mutableListOf(), this)
        initRecyclerView()

        presenter = BaseListPresenter(this, activity)
        presenter.onGetMovies()
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(context, GridLayoutManager.VERTICAL)

        gridLayoutManager.spanCount = 2
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager.spanCount = 4
        }

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun updateScreen(movieList: List<Movie>) {
        adapter.updateList(movieList)
    }

    override fun messageError(message: String) {

    }

    override fun onClickItem(movie: Movie) {
        presenter.showDetail(movie)
    }

}