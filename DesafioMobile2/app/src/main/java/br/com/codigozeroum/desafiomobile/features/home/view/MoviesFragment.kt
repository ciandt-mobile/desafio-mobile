/*
 * MoviesFragment.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:15
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.MovieAdapter
import br.com.codigozeroum.desafiomobile.features.MovieDetailActivity
import br.com.codigozeroum.desafiomobile.features.model.ResultItem
import br.com.codigozeroum.desafiomobile.features.viewModel.MoviesFragmentViewModel
import br.com.codigozeroum.desafiomobile.projectStructure.BaseFragment
import br.com.codigozeroum.desafiomobile.projectStructure.RecyclerViewDelegate
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState
import kotlinx.android.synthetic.main.fragment_movies.*
import br.com.codigozeroum.desafiomobile.utils.SpacesItemDecoration


class MoviesFragment : BaseFragment() , RecyclerViewDelegate<ResultItem>{

    lateinit var viewModel: MoviesFragmentViewModel
    var tab: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab = arguments?.getString("tab")

        configureView(view)
        configureViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun configureView(view: View) {

        gridView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.margin_10dp)
        gridView.addItemDecoration(SpacesItemDecoration(spacingInPixels))

    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel::class.java)

        viewModel.register(this, Observer { newState->
            when(newState){
                ViewModelState.Success -> {
                    bindView()
                }
            }
        })
        viewModel.getUpacomingMovies()
    }

    override fun bindView() {
        val adapter = MovieAdapter(viewModel, this@MoviesFragment)
        gridView.adapter = adapter
    }

    override fun onItemClickListener(view: View, position: Int, obj: ResultItem?) {
        startActivity(Intent(context, MovieDetailActivity::class.java))
    }

    companion object {
        @JvmStatic
        fun newInstance(tab: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString("tab", tab)
                }
            }
    }
}
