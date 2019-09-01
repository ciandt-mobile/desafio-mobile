/*
 * HomeActivity.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 03:15
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.view

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import br.com.codigozeroum.desafiomobile.R
import br.com.codigozeroum.desafiomobile.features.viewModel.HomeViewModel
import br.com.codigozeroum.desafiomobile.features.model.ViewPagerAdapter
import br.com.codigozeroum.desafiomobile.projectStructure.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {

    lateinit var viewModel: HomeViewModel

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
            toast("Ok Refresh")
            swiperefresh.isRefreshing = false
        }

        btnUpcoming.isSelected = true
        btnUpcoming.setTextColor(Color.BLACK)

        btnUpcoming.setOnClickListener { viewPager.setCurrentItem(0, true) }
        btnTopRated.setOnClickListener { viewPager.setCurrentItem(1, true) }
        btnPopular.setOnClickListener { viewPager.setCurrentItem(2, true) }
        btnSearch.setOnClickListener { viewPager.setCurrentItem(3, true) }


        val fragmentList = mutableListOf<Fragment>()
        val upcomingFragment = MoviesFragment.newInstance("upcoming")
        val topRatedFragment = MoviesFragment.newInstance("top_rated")
        val popularFragment = MoviesFragment.newInstance("popular")
        val searchFragment = SearchMovieFragment.newInstance()

        fragmentList.add(upcomingFragment)
        fragmentList.add(topRatedFragment)
        fragmentList.add(popularFragment)
        fragmentList.add(searchFragment)

        val adapter = ViewPagerAdapter(supportFragmentManager, fragmentList)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                //toast(position.toString())
                bindTabButtons(position)
            }

        })

    }

    override fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun bindView() {}

    @SuppressLint("NewApi")
    private fun bindTabButtons(positon: Int){
        when(positon){
            0-> {
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
