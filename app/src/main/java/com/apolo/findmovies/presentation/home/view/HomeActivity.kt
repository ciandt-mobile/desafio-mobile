package com.apolo.findmovies.presentation.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apolo.findmovies.R
import com.apolo.findmovies.data.model.MovieDetailViewModel
import com.apolo.findmovies.data.model.MovieInfoViewModel
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.presentation.home.viewModel.HomeViewModel
import com.apolo.findmovies.presentation.movieDetail.view.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        movies_list.adapter = MoviesAdapter(
            listOf(
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020"),
                MovieViewModel("", "Era um vez", "10/12/2020")
            )
        ) {
            startActivity(
                MovieDetailActivity.getStartIntent(
                    this,
                    MovieDetailViewModel(
                        "",
                        "Titanic",
                        "13/02",
                        "196m",
                        "Science Fiction, Action, Drama",
                        "Blalblaslbasob f msa mbpsam bsomb pasmbp omasp mbpsam pamspo mpsam bpoasm pbomaspom b",
                        listOf(
                            MovieInfoViewModel("", "Apolo", "Herói"),
                            MovieInfoViewModel("", "Leonardo", "Herói"),
                            MovieInfoViewModel("", "Luís", "Herói"),
                            MovieInfoViewModel("", "Henrique", "Herói"),
                            MovieInfoViewModel("", "Tássio", "Herói"),
                            MovieInfoViewModel("", "Toníssio", "Herói"),
                            MovieInfoViewModel("", "Jefferson", "Herói")
                        )
                    )

                )
            )
        }

//        homeViewModel.onViewReady()
    }
}
