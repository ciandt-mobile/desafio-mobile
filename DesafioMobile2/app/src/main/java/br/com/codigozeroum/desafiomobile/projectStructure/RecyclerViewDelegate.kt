/*
 * RecyclerViewDelegate.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 05:34
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.projectStructure

import android.view.View

interface RecyclerViewDelegate <in T> {
    fun onItemClickListener(view: View, position: Int, obj: T? )
}