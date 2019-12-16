package com.nurik.desafiomobile.ui.moviesList.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nurik.desafiomobile.ui.moviesList.fragment.PopularListFragment
import com.nurik.desafiomobile.ui.moviesList.fragment.UpcomingListFragment

class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment {
        return if (position == 0 ) {
            UpcomingListFragment()
        }else{
            PopularListFragment()
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }
}
