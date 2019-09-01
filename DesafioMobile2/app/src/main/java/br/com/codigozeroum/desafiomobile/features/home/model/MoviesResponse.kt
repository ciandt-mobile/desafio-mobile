/*
 * MoviesResponsekt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:14
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.model

data class MoviesResponse(
    val page: Int? = null,
    val results: MutableList<ResultItem>? = mutableListOf(),
    val total_pages: Int? = null,
    val total_results: Int? = null
)