/*
 * HomeActivity.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:15
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.home.view

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.home.viewModel.HomeViewModel
import br.com.codigozeroum.desafiomobile.features.home.model.ViewPagerAdapter
import br.com.codigozeroum.desafiomobile.projectStructure.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {

    lateinit var viewModel: HomeViewModel

    var upcomingFragment: MoviesFragment = MoviesFragment()
    var topRatedFragment: MoviesFragment = MoviesFragment()
    var popularFragment: MoviesFragment = MoviesFragment()
    var searchFragment: SearchMovieFragment = SearchMovieFragment()

    var adapter: ViewPagerAdapter? = null
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        configureView()
        configureViewModel()
        bindView()
    }

    @SuppressLint("NewApi")
    override fun configureView() {

        swiperefresh.setOnRefreshListener {
            viewPager.removeAllViews()
            viewPager.adapter = null
            configureViewPager(currentPosition)
            swiperefresh.isRefreshing = false
        }

        btnUpcoming.isSelected = true
        btnUpcoming.setTextColor(Color.BLACK)

        btnUpcoming.setOnClickListener { viewPager.setCurrentItem(0, true) }
        btnTopRated.setOnClickListener { viewPager.setCurrentItem(1, true) }
        btnPopular.setOnClickListener { viewPager.setCurrentItem(2, true) }
        btnSearch.setOnClickListener { viewPager.setCurrentItem(3, true) }

        configureViewPager(0)

    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun bindView() {
        tabSelectedLabel.text = getString(R.string.upcomming_movies)
    }

    private fun configureViewPager(currentPos: Int){
        val fragmentList = mutableListOf<Fragment>()
        upcomingFragment = MoviesFragment.newInstance("upcoming")
        topRatedFragment = MoviesFragment.newInstance("top_rated")
        popularFragment = MoviesFragment.newInstance("popular")
        searchFragment = SearchMovieFragment.newInstance()

        fragmentList.add(upcomingFragment)
        fragmentList.add(topRatedFragment)
        fragmentList.add(popularFragment)
        fragmentList.add(searchFragment)

        adapter = ViewPagerAdapter(supportFragmentManager, fragmentList)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                currentPosition = position
                bindTabButtons(position)
            }
        })

        when(currentPos){
            0->viewPager.setCurrentItem(0, true)
            1->viewPager.setCurrentItem(1, true)
            2->viewPager.setCurrentItem(2, true)
            3->viewPager.setCurrentItem(3, true)
        }
    }

    @SuppressLint("NewApi")
    private fun bindTabButtons(positon: Int){
        when(positon){
            0-> {
                tabSelectedLabel.visibility = View.VISIBLE
                tabSelectedLabel.text = getString(R.string.upcomming_movies)
                swiperefresh.isEnabled = true

                btnUpcoming.isSelected = true
                btnUpcoming.setTextColor(Color.BLACK)

                btnTopRated.isSelected = false
                btnTopRated.setTextColor(Color.WHITE)
                btnPopular.isSelected = false
                btnPopular.setTextColor(Color.WHITE)
                btnSearch.isSelected = false
                btnSearch.setTextColor(Color.WHITE)
                btnSearch.compoundDrawableTintList = ColorStateList.valueOf(Color.WHITE)
            }
            1->{
                tabSelectedLabel.visibility = View.VISIBLE
                tabSelectedLabel.text = getString(R.string.top_rated_movies)
                swiperefresh.isEnabled = true

                btnUpcoming.isSelected = false
                btnUpcoming.setTextColor(Color.WHITE)

                btnTopRated.isSelected = true
                btnTopRated.setTextColor(Color.BLACK)

                btnPopular.isSelected = false
                btnPopular.setTextColor(Color.WHITE)
                btnSearch.isSelected = false
                btnSearch.setTextColor(Color.WHITE)
                btnSearch.compoundDrawableTintList = ColorStateList.valueOf(Color.WHITE)
            }

            2->{
                tabSelectedLabel.visibility = View.VISIBLE
                tabSelectedLabel.text = getString(R.string.popular_movies)
                swiperefresh.isEnabled = true

                btnUpcoming.isSelected = false
                btnUpcoming.setTextColor(Color.WHITE)
                btnTopRated.isSelected = false
                btnTopRated.setTextColor(Color.WHITE)

                btnPopular.isSelected = true
                btnPopular.setTextColor(Color.BLACK)

                btnSearch.isSelected = false
                btnSearch.setTextColor(Color.WHITE)
                btnSearch.compoundDrawableTintList = ColorStateList.valueOf(Color.WHITE)
            }
            3->{
                tabSelectedLabel.visibility = View.GONE
                swiperefresh.isRefreshing = false
                swiperefresh.isEnabled = false

                btnUpcoming.isSelected = false
                btnUpcoming.setTextColor(Color.WHITE)
                btnTopRated.isSelected = false
                btnTopRated.setTextColor(Color.WHITE)
                btnPopular.isSelected = false
                btnPopular.setTextColor(Color.WHITE)

                btnSearch.isSelected = true
                btnSearch.setTextColor(Color.BLACK)
                btnSearch.compoundDrawableTintList = ColorStateList.valueOf(Color.BLACK)
            }
        }
    }

}
