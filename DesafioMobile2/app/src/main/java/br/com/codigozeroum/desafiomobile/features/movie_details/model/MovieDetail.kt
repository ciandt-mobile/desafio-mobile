/*
 * MovieDetail.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 03:44
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.movie_details.model

data class MovieDetail(
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    var poster_path: String,
    val release_date: String,
    val title: String,
    val runtime: Int
)