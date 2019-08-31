/*
 * PopularResponse.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:14
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.model

data class PopularResponse(
    val page: Int? = null,
    val results: MutableList<Result>? = mutableListOf(),
    val total_pages: Int? = null,
    val total_results: Int? = null
)