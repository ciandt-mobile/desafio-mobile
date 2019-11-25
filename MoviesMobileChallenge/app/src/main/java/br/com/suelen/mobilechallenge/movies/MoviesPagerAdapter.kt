package br.com.suelen.mobilechallenge.movies

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.suelen.mobilechallenge.movies.popular.PopularFragment
import br.com.suelen.mobilechallenge.movies.upcoming.UpcomingFragment
import java.lang.IndexOutOfBoundsException

class MoviesPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {

    companion object {
        const val UPCOMING_MOVIES_PAGE_INDEX = 0
        const val POPULAR_MOVIES_PAGE_INDEX = 1
    }

    private val fragmentsCreator : Map<Int, () -> Fragment> = mapOf(
        UPCOMING_MOVIES_PAGE_INDEX to { UpcomingFragment() },
        POPULAR_MOVIES_PAGE_INDEX to { PopularFragment() }
    )

    override fun getItemCount(): Int = fragmentsCreator.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}