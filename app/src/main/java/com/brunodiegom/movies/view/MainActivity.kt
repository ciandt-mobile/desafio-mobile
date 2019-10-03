package com.brunodiegom.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brunodiegom.movies.R

/**
 * [AppCompatActivity] used to present the list of items.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
