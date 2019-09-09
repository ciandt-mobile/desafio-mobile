package com.conrradocamacho.desafio.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.desafio.R
import com.conrradocamacho.desafio.entity.FragmentMovie
import com.conrradocamacho.desafio.view.adapter.MoviePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Conrrado Camacho on 01/09/2019.
 * con.webmaster@gmail.com
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentList: MutableList<FragmentMovie> = arrayListOf()
        fragmentList.add(FragmentMovie(getString(R.string.tab_upcoming), UpcomingFragment()))
        fragmentList.add(FragmentMovie(getString(R.string.tab_popular), PopularFragment()))

        val fragmentAdapter = MoviePagerAdapter(supportFragmentManager, fragmentList)
        viewPager.adapter = fragmentAdapter

        tabLayout.setupWithViewPager(viewPager)
    }
}