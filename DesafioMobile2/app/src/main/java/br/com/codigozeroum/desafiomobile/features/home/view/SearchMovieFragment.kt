/*
 * SearchMovieFragment.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:15
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.view

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.home.model.MovieAdapter
import br.com.codigozeroum.desafiomobile.features.home.model.ResultItem
import br.com.codigozeroum.desafiomobile.features.home.viewModel.SearchMovieViewModel
import br.com.codigozeroum.desafiomobile.features.movie_details.MovieDetailActivity
import br.com.codigozeroum.desafiomobile.projectStructure.BaseFragment
import br.com.codigozeroum.desafiomobile.projectStructure.RecyclerViewDelegate
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState
import br.com.codigozeroum.desafiomobile.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.android.synthetic.main.fragment_search_movie.view.*

class SearchMovieFragment : BaseFragment(), RecyclerViewDelegate<ResultItem> {

    private lateinit var viewModel: SearchMovieViewModel
    var totalPages = 1
    var currentPage = 1
    var currentQuery = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureView(view)
        configureViewModel()
        bindView()
    }

    override fun configureView(view: View) {
        view.searchGridView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.margin_10dp)
        view.searchGridView.addItemDecoration(SpacesItemDecoration(spacingInPixels))

        previousPage.setOnClickListener {
            if(currentPage > 1){
                currentPage -= 1
                viewModel.searchMovie(currentQuery, currentPage)

            }
        }
        nextPage.setOnClickListener {
            if(currentPage < totalPages){
                currentPage += 1
                viewModel.searchMovie(currentQuery, currentPage)

            }
        }
    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(SearchMovieViewModel::class.java)

        viewModel.register(this, Observer { newState->
            when(newState){
                ViewModelState.Success -> {
                    searchGridView.adapter?.notifyDataSetChanged()
                    totalPages = viewModel.totalPages
                    bindView()
                }
            }
        })
    }

    override fun bindView() {
        val adapter = MovieAdapter(viewModel, this@SearchMovieFragment)
        searchGridView.adapter = adapter

        searchMovieInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                currentQuery = p0.toString()
                viewModel.searchMovie(p0.toString())
            }
        })

        currentPageLabel.text = currentPage.toString()
        totalPagesLabel.text = " de $totalPages"
    }

    override fun onItemClickListener(view: View, position: Int, obj: ResultItem?) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("movieId", obj!!.id)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = SearchMovieFragment()
    }


}
