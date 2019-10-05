package com.brunodiegom.movies.extension

import com.brunodiegom.movies.model.Movie
import java.text.SimpleDateFormat
import java.util.Locale

private const val INPUT_FORMAT = "yyyy-MM-dd"
private const val OUTPUT_FORMAT = "dd/MM/yyyy"

/**
 * Receives a [Movie.releaseDate] following the [INPUT_FORMAT], then returns as the [OUTPUT_FORMAT]
 */
fun Movie.getFormattedReleaseDate(): String {
    val date = SimpleDateFormat(INPUT_FORMAT, Locale.getDefault()).parse(this.releaseDate)
    return SimpleDateFormat(OUTPUT_FORMAT, Locale.getDefault()).format(date)
}
