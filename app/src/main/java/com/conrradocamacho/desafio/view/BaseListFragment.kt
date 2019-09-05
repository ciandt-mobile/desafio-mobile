package com.conrradocamacho.desafio.view

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
class BaseListFragment: Fragment(), BaseListContract.View {

    private lateinit var adapter: MovieListAdapter
    private lateinit var presenter: BaseListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        adapter = MovieListAdapter(mutableListOf())
        presenter = BaseListPresenter(this, activity)
        initRecyclerView()
        presenter.onGetMovies()
    }

    private fun initRecyclerView() {
//        val movieList: MutableList<Movie> = mutableListOf()
//        movieList.add(Movie("Teste 1", "01/05/2019"))
//        movieList.add(Movie("Teste 2", "01/06/2019"))
//        movieList.add(Movie("Teste 3", "01/07/2019"))
//
//        recyclerView.layoutManager = GridLayoutManager(context, GridLayoutManager.VERTICAL)
//        recyclerView.adapter = MovieListAdapter(movieList)
//
//        val movieList: MutableList<Movie> = mutableListOf()
//        movieList.add(Movie("Teste 1", "01/05/2019"))
//        movieList.add(Movie("Teste 2", "01/06/2019"))
//        movieList.add(Movie("Teste 3", "01/07/2019"))
//        movieList.add(Movie("Teste 4", "01/08/2019"))
//        movieList.add(Movie("Teste 5", "01/09/2019"))
//        movieList.add(Movie("Teste 6", "01/10/2019"))
//        movieList.add(Movie("Teste 7", "01/09/2019"))
//        movieList.add(Movie("Teste 8", "01/10/2019"))

        val scrollListener = object : HidingScrollListener() {
            override fun onHide() {
            }

            override fun onShow() {
            }
        }

        val gridLayoutManager = GridLayoutManager(context, GridLayoutManager.VERTICAL)
        gridLayoutManager.spanCount = 2

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(scrollListener)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun updateScreen(movieList: List<Movie>) {
        adapter.updateList(movieList)
    }

    override fun messageError(message: String) {

    }

}