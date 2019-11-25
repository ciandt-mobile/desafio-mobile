package br.com.suelen.mobilechallenge.di

import androidx.lifecycle.ViewModel
import br.com.suelen.mobilechallenge.movies.detail.MovieDetailFragment
import br.com.suelen.mobilechallenge.movies.detail.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun movieDetailFragment() : MovieDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindViewModel(viewModel : MovieDetailViewModel) : ViewModel
}