/*
 * ProductionCompany.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 04:13
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.movie_details.model

data class ProductionCompany(
    val id: Int,
    var logo_path: String,
    val name: String,
    val origin_country: String
)