package br.com.suelen.moviesmobilechallenge

import androidx.appcompat.widget.Toolbar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.suelen.mobilechallenge.MainActivity
import br.com.suelen.mobilechallenge.R
import br.com.suelen.mobilechallenge.utilities.EspressoIdlingResource
import br.com.suelen.moviesmobilechallenge.utilities.DataBindingIdlingResource
import br.com.suelen.moviesmobilechallenge.utilities.monitorActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.hamcrest.Description
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.reflect.KClass

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @get:Rule
    val rule = DaggerTestApplicationRule()

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun clickMovie_OpenMovieDetail() {

        val scenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(scenario)

        onView(withText("Charlie's Angels")).perform(click())
        onView(isAssignableFrom(CollapsingToolbarLayout::class.java)).
            check(matches(CustomMatchers.withCollapsingToolbarTitle("Charlie's Angels")))
    }

    @Test
    fun openPopularMovie() {

        val scenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(scenario)

        onView(withId(R.id.navigation_popular)).perform(click())
        onView(withText("Joker")).perform(click())

        onView(isAssignableFrom(CollapsingToolbarLayout::class.java)).
            check(matches(CustomMatchers.withCollapsingToolbarTitle("Joker")))
    }

    object CustomMatchers {

        fun withCollapsingToolbarTitle(title : String) =
            object : TypedBoundedMatcher<Any, CollapsingToolbarLayout>(CollapsingToolbarLayout::class) {
                private lateinit var titleText: String

                override fun matchesSafely(view: CollapsingToolbarLayout): Boolean {
                    titleText = title
                    return view.title == titleText
                }

                override fun describeTo(description: Description?) = description.use {
                    appendText("with collapsing toolbar title: ")
                    appendValue(title)
                }

                override fun describeMismatchTyped(item: CollapsingToolbarLayout, description: Description) = description.use {
                    appendText("with collapsing toolbar title: ")
                    appendText("resolved as: ")
                    appendValue(titleText)
                    appendText("and actual title: ")
                    appendValue(item.title)
                }
            }

        abstract class TypedBoundedMatcher<T : Any, S : T>(
            private val expectedType: KClass<out S>
        ) : BoundedMatcher<T, S>(expectedType.java) {

            @Suppress("UNCHECKED_CAST")
            override fun describeMismatch(item: Any?, description: Description) = when {
                item == null || !expectedType.java.isInstance(item) -> super.describeMismatch(item, description)
                else -> describeMismatchTyped(item as S, description)
            }

            abstract fun describeMismatchTyped(item: S, description: Description)
        }

        fun Description?.use(block: Description.() -> Any?) = this?.let(block)?.let { Unit } ?: Unit

    }
}