package br.com.suelen.mobilechallenge.di

import androidx.lifecycle.ViewModel
import br.com.suelen.mobilechallenge.movies.upcoming.UpcomingFragment
import br.com.suelen.mobilechallenge.movies.upcoming.UpcomingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UpcomingMoviesModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun upcomingFragment() : UpcomingFragment

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingViewModel::class)
    abstract fun bindViewModel(viewModel : UpcomingViewModel) : ViewModel
}