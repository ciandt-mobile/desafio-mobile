package com.nurik.desafiomobile

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nurik.desafiomobile.ui.moviesList.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.switch_header.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)
    lateinit var mainActivity: MainActivity
    lateinit var context: Context

    @Before
    fun setup(){
        mainActivity = activityRule.activity
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun verify_switchIsCalling_rightPages(){
        mainActivity.runOnUiThread {
            mainActivity.upcoming_button.performClick()
            assertEquals(0, mainActivity.viewPager.currentItem)
            mainActivity.popular_button.performClick()
            assertEquals(1, mainActivity.viewPager.currentItem)
        }
    }

    @Test
    fun verify_switchIsRenaming_rightPages(){
        mainActivity.runOnUiThread {
            mainActivity.upcoming_button.performClick()
            assertEquals("Upcoming Movies", mainActivity.header_title.text)
            mainActivity.popular_button.performClick()
            assertEquals("Popular Movies", mainActivity.header_title.text)
        }
    }
}