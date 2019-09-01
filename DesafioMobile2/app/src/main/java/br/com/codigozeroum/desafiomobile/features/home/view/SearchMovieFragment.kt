/*
 * SearchMovieFragment.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:15
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.home.viewModel.SearchMovieViewModel
import br.com.codigozeroum.desafiomobile.projectStructure.BaseFragment

class SearchMovieFragment : BaseFragment() {

    private lateinit var viewModel: SearchMovieViewModel

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
    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(SearchMovieViewModel::class.java)
    }

    override fun bindView() {
    }

    companion object {
        fun newInstance() = SearchMovieFragment()
    }


}
