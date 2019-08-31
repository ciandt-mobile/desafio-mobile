/*
 * RecyclerViewDataSource.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 05:33
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.projectStructure

interface RecyclerViewDataSource<out T> {
    fun getItemCount(): Int
    fun getViewTypeFor(position: Int): Int
    fun getItemFor(position: Int): T
}