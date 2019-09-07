package com.apolo.findmovies.base.view

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.apolo.findmovies.data.model.BaseViewModel

abstract class BaseActivity : AppCompatActivity() {


    protected abstract fun createPresenter(context: Context): BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

}