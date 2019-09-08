package com.apolo.findmovies.base

import java.text.SimpleDateFormat
import java.util.*

fun String.getYear(): Int {
    try {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)?.let { parsedDate ->
            return Calendar.getInstance().let { calendar ->
                calendar.time = parsedDate
                calendar
                    .get(Calendar.YEAR)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return -1
}