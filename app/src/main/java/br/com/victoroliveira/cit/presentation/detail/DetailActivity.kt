package br.com.victoroliveira.cit.presentation.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.victoroliveira.cit.BuildConfig
import br.com.victoroliveira.cit.R
import br.com.victoroliveira.cit.data.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModel()
    private val detailAdapter: DetailAdapter by inject()
    private lateinit var detailGridLayoutManager: GridLayoutManager

    lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movie = intent?.extras?.getSerializable("movie") as Movie
        supportActionBar?.title = movie.title

        loadMovie(movie)
        setupDetailRecyclerView()

        viewModel.getCast(movie.id)
        viewModel.getDetail(movie.id)

        viewModel.listCast.observe(this, Observer {
            detailAdapter.addCast(it)
        })
    }

    private fun loadMovie(movie: Movie) {
        Glide.with(this).load(BuildConfig.API_IMAGE_FULL + movie.backdrop_path)
            .into(detail_image)
        detail_title.text = movie.title
        detail_date.text =
            SimpleDateFormat("yyyy").format(
                SimpleDateFormat("yyyy-MM-dd").parse(movie.release_date)
            )

        viewModel.runTime.observe(this, Observer {
            detail_duration.text = it.toString().plus("m")
        })

        viewModel.listGenre.observe(this, Observer {
            var genre = ""
            it.forEach {
                genre = genre.plus(it.name).plus("  ")
            }
            detail_genre.text = genre
        })
        detail_overview.text = movie.overview
    }

    private fun setupDetailRecyclerView() {
        detailGridLayoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)

        detail_recycler_cast.apply {
            adapter = detailAdapter
            setHasFixedSize(true)
            this.layoutManager = detailGridLayoutManager
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
