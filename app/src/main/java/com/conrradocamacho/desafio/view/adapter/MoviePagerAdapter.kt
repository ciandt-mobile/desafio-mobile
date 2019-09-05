package com.conrradocamacho.desafio.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.conrradocamacho.desafio.entity.FragmentMovie

/**
 * Created by Conrrado Camacho on 01/09/2019.
 * con.webmaster@gmail.com
 */
class MoviePagerAdapter(fm: FragmentManager, val fragmentList: List<FragmentMovie>): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragmentList[position].fragment
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList[position].title
    }
}