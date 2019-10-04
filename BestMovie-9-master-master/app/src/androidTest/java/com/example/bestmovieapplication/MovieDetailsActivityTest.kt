package com.example.bestmovieapplication

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.bestmovieapplication.feature.movies.PopularMoviesActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieDetailActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(PopularMoviesActivity::class.java)

    @Test
    fun movieDetailActivityTest() {
        onView(
            Matchers.allOf(
                childAtPosition(
                    Matchers.allOf(
                        withId(R.id.recycler_view_movies),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        SystemClock.sleep(6000)
        onView(withText("Joker")).perform(click())
        SystemClock.sleep(6000)

        val movieName = onView(
            Matchers.allOf(
                withText("Joker"), ViewMatchers.isDisplayed()
            )
        )
        movieName.check(ViewAssertions.matches(withText("Joker")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                        view == parent.getChildAt(position)
            }
        }
    }

    @Test
    fun funClickAtPopularCv() {

        onView(withId(R.id.popular_movies_cv)).perform(click())
        SystemClock.sleep(2000)

        val movieName = onView(
            Matchers.allOf(
                withId(R.id.header_text_view),
                ViewMatchers.isDisplayed()
            )
        )
        movieName.check(ViewAssertions.matches(withText("POPULAR")))
    }

    @Test
    fun funClickAtUpcomingCv() {
        SystemClock.sleep(5000)
        onView(withId(R.id.upcoming_movies_cv)).perform(click())
        SystemClock.sleep(5000)

        val movieName = onView(
            Matchers.allOf(
                withId(R.id.header_text_view),
                ViewMatchers.isDisplayed()
            )
        )
        movieName.check(ViewAssertions.matches(withText("UPCOMING")))
    }

}