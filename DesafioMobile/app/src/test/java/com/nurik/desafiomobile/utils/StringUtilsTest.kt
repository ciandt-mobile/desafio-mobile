package com.nurik.desafiomobile.utils

import org.junit.Assert.*
import org.junit.Test

class StringUtilsTest{
    @Test
    fun verify_dateConversion_returningCorrectFormat (){
        val strDate = "2019-12-04"
        val resultStr = StringUtils.convertStringDateToBRPattern(strDate)
        assertEquals("04/12/19", resultStr)
    }

    @Test
    fun verify_dateConversion_returningCorrectYear (){
        val strDate = "2019-12-04"
        val resultStr = StringUtils.getYearFromDateString(strDate)
        assertEquals("2019", resultStr)
    }
}