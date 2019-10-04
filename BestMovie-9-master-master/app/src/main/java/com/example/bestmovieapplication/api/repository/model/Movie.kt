package com.example.bestmovieapplication.api.repository.model

import java.text.SimpleDateFormat
import java.util.*

// Movie object
class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: Array<movieGenre>,
    val release_date: String,
    val poster_path: String,
    val runtime: String,
    val credits: credits
) {

    @Transient var releaseDateFormat: Calendar = Calendar.getInstance()

    init {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        releaseDateFormat.time = simpleDateFormat.parse(release_date)
    }
}
