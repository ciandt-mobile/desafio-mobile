package com.conrradocamacho.desafio

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.conrradocamacho.desafio.contract.DetailContract
import com.conrradocamacho.desafio.json.DetailJson
import com.conrradocamacho.desafio.network.bean.Detail
import com.conrradocamacho.desafio.utils.Util
import com.conrradocamacho.desafio.view.DetailActivity
import com.google.gson.Gson
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Conrrado Camacho on 09/09/2019.
 * con.webmaster@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class DetailTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(DetailActivity::class.java)

    private lateinit var view: DetailContract.View
    private lateinit var detail: Detail

    @Before
    fun setup() {
        view = rule.activity

        val gson = Gson()
        detail = gson.fromJson(DetailJson.getDetail, Detail::class.java)
    }

    @Test
    fun testUpdateScreenWithDetails() {
        rule.runOnUiThread {
            view.updateScreenWithDetails(detail)
        }

        val year = Util.getYearFromDateServer(detail.releaseDate)
        val genres = Util.getTextFromItemList(detail.genres ?: arrayListOf())
        val text = "$year - ${detail.runtime}min - $genres"

        Espresso.onView(ViewMatchers.withId(R.id.movieTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText(detail.title)))
        Espresso.onView(ViewMatchers.withId(R.id.aditionalInformation))
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
        Espresso.onView(ViewMatchers.withId(R.id.overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(detail.overview)))
    }

}