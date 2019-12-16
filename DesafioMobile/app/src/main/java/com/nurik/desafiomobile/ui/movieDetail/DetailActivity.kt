package com.nurik.desafiomobile.ui.movieDetail

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nurik.desafiomobile.R
import com.nurik.desafiomobile.data.MoviesApi
import com.nurik.desafiomobile.data.MoviesRepository
import com.nurik.desafiomobile.pojo.Movie
import com.nurik.desafiomobile.ui.moviesList.MovieListViewModelFactory
import com.nurik.desafiomobile.utils.ItemClickListener
import com.nurik.desafiomobile.utils.StringUtils
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity : AppCompatActivity() {

    private lateinit var factory: MovieViewModelFactory
    private lateinit var mListViewModel: MovieViewModel
    val base_img_url = "http://image.tmdb.org/t/p/w185/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
    }

    override fun onStart() {
        super.onStart()
        initFactory()
        initViewModel()
        setData()
    }

    private fun setData() {
        mListViewModel.setMovie(intent.getSerializableExtra("movie") as? Movie)
        mListViewModel.getGenreMovies()
        mListViewModel.getMovieDetail()
    }

    private fun initViewModel() {
        mListViewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
        mListViewModel.selectedMovie.observe(this, Observer { movie ->
            if(movie!=null){
                setDataOnUi(movie, mListViewModel.genreValues)
            }
        })
    }

    fun setDataOnUi(movie: Movie, genrevalues: List<String>) {
        setMainData(movie, genrevalues)
        setCastData(movie)
    }

    private fun setCastData(movie: Movie) {
        if(movie.credits!=null){
            cast_recycler_view.setHasFixedSize(true)
            cast_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
            cast_recycler_view.adapter = CastAdapter(movie.credits!!.cast)
        }
    }

    private fun setMainData(movie: Movie, genrevalues: List<String>) {
        Glide.with(this)
                .load(base_img_url + movie.backdrop_path)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(movie_image)
        movie_name.text = movie.original_title
        movie_year.text = StringUtils.getYearFromDateString(movie.release_date)
        if (movie.overview != null) movie_description.text = movie.overview
        if(movie.runtime != null) {
            movie_runtime.text = StringBuilder()
                    .append(movie.runtime)
                    .append(" min |").toString()

        }
        movie_genre.text = getStringWithComma(genrevalues)
    }

    private fun getStringWithComma(genrevalues: List<String>): String {
        return genrevalues.joinToString()
    }

    private fun initFactory() {
        val api = MoviesApi()
        val repository = MoviesRepository(api)
        factory = MovieViewModelFactory(repository)
    }
}
