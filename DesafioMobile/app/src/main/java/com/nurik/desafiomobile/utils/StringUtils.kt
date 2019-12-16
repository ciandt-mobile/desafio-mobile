package com.nurik.desafiomobile.utils

import java.text.SimpleDateFormat

object StringUtils {
    fun convertStringDateToBRPattern(value: String): String {
        val myDate= SimpleDateFormat("yyyy-MM-dd").parse(value)
        return SimpleDateFormat("dd/MM/yy").format(myDate)
    }

    fun getYearFromDateString(value: String): String{
        val date= SimpleDateFormat("yyyy-MM-dd").parse(value)
        return SimpleDateFormat("yyyy").format(date)
    }
}
