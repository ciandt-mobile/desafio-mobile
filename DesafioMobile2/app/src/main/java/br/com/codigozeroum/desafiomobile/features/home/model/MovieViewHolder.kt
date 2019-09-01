/*
 * MovieViewHolder.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 03:41
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.model

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.codigozeroum.desafiomobile.projectStructure.RecyclerViewDelegate
import br.com.codigozeroum.desafiomobile.utils.ImageLoader
import kotlinx.android.synthetic.main.item_movie_grid.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieViewHolder(itemView: View, private val delegate: RecyclerViewDelegate<ResultItem>): RecyclerView.ViewHolder(itemView) {

    @SuppressLint("NewApi")
    fun bind(item: ResultItem){

        ImageLoader.loadImageWith(itemView.context, "https://image.tmdb.org/t/p/w154${item.poster_path}", itemView.folderImage)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(item.release_date, formatter)

        val formatterBR = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        itemView.releaseDate.text = date.format(formatterBR)
        itemView.voteAverage.text = item.vote_average.toString()
        itemView.popularity.text = item.popularity.toString()
        itemView.title.text = item.title

        itemView.setOnClickListener { delegate.onItemClickListener(itemView, adapterPosition, item) }

    }
}