package com.movies.appmoviescit.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appmoviescit.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        main_tab.addTab(main_tab.newTab().setText(R.string.popular))
        main_tab.addTab(main_tab.newTab().setText(R.string.upcoming))
        main_tab.tabGravity = TabLayout.GRAVITY_FILL

        val homeAdapter = HomeAdapter(supportFragmentManager, main_tab.tabCount)
        main_view_pager.adapter = homeAdapter

        main_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tab))

        main_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                main_view_pager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}
