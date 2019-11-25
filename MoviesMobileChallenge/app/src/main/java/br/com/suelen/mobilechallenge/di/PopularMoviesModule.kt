package br.com.suelen.mobilechallenge.di

import androidx.lifecycle.ViewModel
import br.com.suelen.mobilechallenge.movies.popular.PopularFragment
import br.com.suelen.mobilechallenge.movies.popular.PopularViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PopularMoviesModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun popularFragment(): PopularFragment

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    abstract fun bindViewModel(viewModel: PopularViewModel): ViewModel
}