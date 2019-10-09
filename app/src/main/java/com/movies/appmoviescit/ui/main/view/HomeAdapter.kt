package com.movies.appmoviescit.ui.main.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.movies.appmoviescit.ui.main.view.fragments.PopularMoviesFragment
import com.movies.appmoviescit.ui.main.view.fragments.UpcomingMoviesFragment

class HomeAdapter(fragmentManager: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fragmentManager, totalTabs) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return PopularMoviesFragment()
        } else {
            return UpcomingMoviesFragment()
        }
    }
}