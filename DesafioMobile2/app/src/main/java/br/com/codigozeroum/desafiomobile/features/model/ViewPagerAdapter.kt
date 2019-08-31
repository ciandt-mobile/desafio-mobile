/*
 * ViewPagerAdapter.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 01:51
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.features.model

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragManager: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fragManager) {

    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getCount(): Int = fragments.size
    override fun getPageTitle(position: Int): CharSequence = ""

}