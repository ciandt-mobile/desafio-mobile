package br.com.suelen.moviesmobilechallenge

import androidx.fragment.app.Fragment
import br.com.suelen.mobilechallenge.MoviesApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class TestMovieApplication : MoviesApplication(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = fragmentInjector
}
