/*
 * MoviesFragment.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:15
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.viewModel.MoviesFragmentViewModel
import br.com.codigozeroum.desafiomobile.projectStructure.BaseFragment
import br.com.codigozeroum.desafiomobile.projectStructure.ViewModelState
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : BaseFragment() {

    lateinit var viewModel: MoviesFragmentViewModel

    var tab: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab = arguments?.getString("tab")

        configureView(view)
        configureViewModel()
        bindView()

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }


    override fun configureView(view: View) {}

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel::class.java)

        viewModel.register(this, Observer { newState->
            when(newState){
                ViewModelState.Success -> {
                    (activity as HomeActivity).toast(viewModel.response.results!!.size.toString())
                }
            }
        })
        viewModel.getUpacomingMovies()
    }

    override fun bindView() {
        testeLabel.text = tab
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
