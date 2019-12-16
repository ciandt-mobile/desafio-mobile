package com.nurik.desafiomobile

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nurik.desafiomobile.pojo.Credit
import com.nurik.desafiomobile.pojo.Movie
import com.nurik.desafiomobile.ui.movieDetail.DetailActivity
import com.nurik.desafiomobile.ui.moviesList.MainActivity
import kotlinx.android.synthetic.main.detail_activity.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(DetailActivity::class.java)
    lateinit var detailActivity: DetailActivity
    lateinit var context: Context
    lateinit var movie: Movie
    val genreList = listOf("valor 1", "valor 2")

    @Before
    fun setup(){
        detailActivity = activityRule.activity
        context = InstrumentationRegistry.getInstrumentation().targetContext
        movie = createDummieMovie()
    }

    private fun createDummieMovie(): Movie {
        return Movie(1,
                "titulo original",
                "2009-12-12",
                "poster",
                "backdrop",
                listOf(15,16,17),
                95,
       "overview", null)
    }

    @Test
    fun verify_onGetData_FieldsLoadRight(){
        detailActivity.runOnUiThread {
            detailActivity.setDataOnUi(createDummieMovie(), genreList)
            assertEquals("titulo original", detailActivity.movie_name.text)
            assertEquals("2009", detailActivity.movie_year.text)
            assertEquals("overview", detailActivity.movie_description.text)
        }
    }
}