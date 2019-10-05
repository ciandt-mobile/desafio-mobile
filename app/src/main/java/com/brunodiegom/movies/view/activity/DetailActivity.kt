package com.brunodiegom.movies.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brunodiegom.movies.model.Detail.Companion.EXTRA_ID
import com.brunodiegom.movies.viewmodel.DetailViewModel
import com.brunodiegom.movies.viewmodel.DetailViewModel.Companion.DEFAULT_ID
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * [AppCompatActivity] used to visually present the data.
 */
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.start(intent.getIntExtra(EXTRA_ID, DEFAULT_ID))
    }
}
