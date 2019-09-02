/*
 * ResultItem.ktm.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 06:59
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.model

data class ResultItem(
    val id: Int,
    val original_title: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
)