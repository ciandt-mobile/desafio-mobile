/*
 * MovieAdapter.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 03:41
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.projectStructure.RecyclerViewDataSource
import br.com.codigozeroum.desafiomobile.projectStructure.RecyclerViewDelegate

class MovieAdapter(private val dataSource : RecyclerViewDataSource<ResultItem>, private val delegate : RecyclerViewDelegate<ResultItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie_grid, parent, false)
        return MovieViewHolder(view, delegate)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.bind(dataSource.getItemFor(position))
        }
    }

    override fun getItemCount(): Int = dataSource.getItemCount()

}