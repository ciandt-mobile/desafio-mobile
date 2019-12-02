package br.com.josericardooliveira.desafiomobile.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MovieQuery (

    @SerializedName("page") val page : Int,
    @SerializedName("total_results") val totalResults : Int,
    @SerializedName("total_pages") val totalPages : Int,
    @SerializedName("results") val results : List<MovieInfo>
)

data class MovieInfo (
//    @SerializedName("popularity") val popularity : Double,
    @SerializedName("id") val id : Int,
//    @SerializedName("video") val video : Boolean,
//    @SerializedName("vote_count") val vote_count : Int,
//    @SerializedName("vote_average") val vote_average : Double,
    @SerializedName("title") val title : String,
    @SerializedName("release_date") val releaseDate : String,
//    @SerializedName("original_language") val original_language : String,
//    @SerializedName("original_title") val original_title : String,
//    @SerializedName("genre_ids") val genre_ids : List<Int>,
//    @SerializedName("backdrop_path") val backdrop_path : String,
//    @SerializedName("adult") val adult : Boolean,
//    @SerializedName("overview") val overview : String,
    @SerializedName("poster_path") val posterPath : String
) {
    fun formattedDate():String {
//        val readFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//        val humanFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//        val date = LocalDate.parse(releaseDate, readFormat)
//        return humanFormat.format(date)
        return releaseDate
    }
}