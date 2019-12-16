package com.nurik.desafiomobile.ui.moviesList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nurik.desafiomobile.R
import com.nurik.desafiomobile.pojo.Movie
import com.nurik.desafiomobile.ui.movieDetail.DetailActivity
import com.nurik.desafiomobile.ui.moviesList.adapter.ViewPagerAdapter
import com.nurik.desafiomobile.utils.ItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.switch_header.*

class MainActivity : AppCompatActivity(), ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initViewPager()
        initSwitch()
    }

    private fun initSwitch() {
        upcoming_button.setOnClickListener { switchOn(true) }
        popular_button.setOnClickListener { switchOn(false) }
    }

    private fun switchOn(value: Boolean) {
        if(value){
            viewPager.currentItem = 0
            header_title.text = getString(R.string.upcoming_movies)
        }else{
            viewPager.currentItem = 1
            header_title.text = getString(R.string.popular_movies)
        }
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    override fun <T : Any> onItemClickListener(item: T) {
        var i = Intent(this, DetailActivity::class.java)
        i.putExtra("movie", item as Movie)
        startActivity(i)
    }
}
