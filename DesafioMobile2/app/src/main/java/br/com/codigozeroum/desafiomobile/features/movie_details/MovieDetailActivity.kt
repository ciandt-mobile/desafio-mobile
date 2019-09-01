/*
 * MovieDetailActivity.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 01/09/19 02:26
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.codigozeroum.desafiomobile.R

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }
}
