package br.com.suelen.moviesmobilechallenge

import br.com.suelen.mobilechallenge.utilities.DateTimeUtil
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test

class DateTimeUtilTest {

    @Test
    fun parseDate() {
        assertEquals("24/11/19", DateTimeUtil.reformatStringDate("2019-11-24"))

        assertNull(DateTimeUtil.parseDate(""))
    }
}