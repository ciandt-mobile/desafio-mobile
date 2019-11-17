package br.com.victoroliveira.cit

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import br.com.victoroliveira.cit.presentation.base.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ScreenTests : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun searchFlow() {
        suspendUntilSuccess({
            onView(withId(R.id.upcoming_fragment_search)).perform(
                click(), typeText("Frozen")
            )
        })
    }

    @Test
    fun clickUpcomingFlow() {
        suspendUntilSuccess({
            onView(withId(R.id.nav_upcoming)).perform(
                click()
            )
        })
    }

    @Test
    fun clickPopularFlow() {
        suspendUntilSuccess({
            onView(withId(R.id.nav_popular)).perform(
                click()
            )
        })
    }
}