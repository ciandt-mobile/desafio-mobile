package com.thiagoseiji.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thiagoseiji.movieapp.R
import com.thiagoseiji.movieapp.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val moviesVM: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val liveData = moviesVM.getData()

        Timber.d(liveData.toString())
    }
}
