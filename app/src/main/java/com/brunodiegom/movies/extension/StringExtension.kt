package com.brunodiegom.movies.extension

import java.text.SimpleDateFormat
import java.util.*

private const val INPUT_FORMAT = "yyyy-MM-dd"
private const val OUTPUT_FORMAT = "dd/MM/yyyy"

/**
 * Receives a [String] following the [INPUT_FORMAT], then returns as the [OUTPUT_FORMAT]
 */
fun String.toDate(): String {
    val date = SimpleDateFormat(INPUT_FORMAT, Locale.getDefault()).parse(this)
    return SimpleDateFormat(OUTPUT_FORMAT, Locale.getDefault()).format(date)
}
