package com.example.bestmovieapplication.feature.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestmovieapplication.Constants.Companion.MOVIE_BASE_URL
import com.example.bestmovieapplication.R
import com.example.bestmovieapplication.api.repository.Apifactory
import com.example.bestmovieapplication.api.repository.model.Movie
import com.example.bestmovieapplication.api.repository.model.cast
import com.example.bestmovieapplication.api.repository.repository.MovieRepository
import com.example.bestmovieapplication.feature.movies.adapter.CastAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movieId = intent.getIntExtra("movieId", -1)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.repository = MovieRepository(Apifactory.tmdbApi)

        movieViewModel.fetchMovie(movieId)

        movieViewModel.specificMovieLiveData.observe(this, Observer { it ->
            it?.let {
                setupUI(it)
            }
        })
    }

    private fun configureRecyclerView(mutableList: MutableList<cast>){

        val recyclerView = movie_cast_rv
        recyclerView.adapter = CastAdapter(mutableList, this)

        val layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL,false)
        recyclerView.layoutManager = layoutManager
    }

    private fun setupUI(it: Movie){

        val genreList = mutableListOf<String>()
        val castList = mutableListOf<cast>()

        for (genre in it.genres){
            genreList.add(genre.name)
        }

        for (cast in it.credits.
            cast){
            castList.add(cast)
        }

        movie_release_data_details.text = it.release_date
        movie_title.text = it.title
        movie_genres_types.text = genreList.toString()
        movie_length.text = it.runtime + "m"
        movie_resume.text = it.overview

        Picasso.get().load(MOVIE_BASE_URL + it.poster_path)
            .placeholder(R.drawable.ic_launcher_background)
            .into(movie_poster_image_view)

        configureRecyclerView(castList)
    }
}
