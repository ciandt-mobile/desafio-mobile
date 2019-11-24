package com.adelannucci.movies

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.adelannucci.movies.data.ApiTheMovieService
import com.adelannucci.movies.data.network.ConnectivityInterceptorImpl
import com.adelannucci.movies.data.network.TheMovieDataSourceImpl
import com.adelannucci.movies.ui.main.SectionsPagerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val apiService = ApiTheMovieService(ConnectivityInterceptorImpl(this.baseContext))
            val dataSource = TheMovieDataSourceImpl(apiService)

            dataSource.downloadedCurrentWeather.observe(
                this,
                Observer {
                   Log.v("ops", "aqui")
                }
            )

            GlobalScope.launch(Dispatchers.Main) {
                dataSource.fetch(1, "en-US")
            }
        }
    }
}