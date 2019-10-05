package com.brunodiegom.movies.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.brunodiegom.movies.R
import com.brunodiegom.movies.databinding.ActivityDetailBinding
import com.brunodiegom.movies.model.Detail.Companion.EXTRA_ID
import com.brunodiegom.movies.view.adapter.CastAdapter
import com.brunodiegom.movies.viewmodel.DetailViewModel
import com.brunodiegom.movies.viewmodel.DetailViewModel.Companion.DEFAULT_ID
import kotlinx.android.synthetic.main.activity_detail.detail_toolbar
import kotlinx.android.synthetic.main.content_detail.cast
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * [AppCompatActivity] used to visually present the data.
 */
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()

    private val adapter by lazy { CastAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()
        viewModel.start(intent.getIntExtra(EXTRA_ID, DEFAULT_ID))
        setupToolbar()
        setupCast()
    }

    private fun bindViewModel() {
        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setupToolbar() {
        detail_toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupCast() {
        cast.adapter = adapter
        viewModel.cast.observe(this, Observer { adapter.replaceItems(it) })
    }
}
