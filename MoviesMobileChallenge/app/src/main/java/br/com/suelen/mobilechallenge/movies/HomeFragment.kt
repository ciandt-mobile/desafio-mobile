package br.com.suelen.mobilechallenge.movies

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import br.com.suelen.mobilechallenge.R
import br.com.suelen.mobilechallenge.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    lateinit var viewPager : ViewPager2
    lateinit var toolbar : Toolbar
    lateinit var navView : BottomNavigationView

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {

            when(position) {
                0 -> {
                    navView.selectedItemId = R.id.navigation_upcoming
                    toolbar.title = "Upcoming"
                }
                1 -> {
                    navView.selectedItemId = R.id.navigation_popular
                    toolbar.title = "Popular"
                }
            }
            super.onPageSelected(position)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewPager = binding.viewPager
        viewPager.adapter = MoviesPagerAdapter(this)

        toolbar = binding.toolbar

        navView = binding.navView
        navView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_upcoming -> {
                    viewPager.currentItem = 0
                    toolbar.title = "Upcoming"
                }
                R.id.navigation_popular -> {
                    viewPager.currentItem = 1
                    toolbar.title = "Popular"
                }
            }
            true
        }

        viewPager.registerOnPageChangeCallback(pageChangeCallback)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }
}