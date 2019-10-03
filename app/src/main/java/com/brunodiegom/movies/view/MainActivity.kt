package com.brunodiegom.movies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brunodiegom.movies.R
import com.brunodiegom.movies.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * [AppCompatActivity] used to present the list of items.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
