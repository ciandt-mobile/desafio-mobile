/*
 * BaseActivity.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 30/08/19 05:35
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.projectStructure

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun configureView()
    protected abstract fun configureViewModel()
    protected abstract fun bindView()

    fun toast( message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}