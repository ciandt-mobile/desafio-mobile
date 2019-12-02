package br.com.josericardooliveira.desafiomobile.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie (

//    @SerializedName("adult") val adult : Boolean,
    @SerializedName("backdrop_path") val backdropPath : String,
//    @SerializedName("belongs_to_collection") val belongs_to_collection : Belongs_to_collection,
//    @SerializedName("budget") val budget : Int,
    @SerializedName("genres") val genres : List<Genres>,
//    @SerializedName("homepage") val homepage : String,
    @SerializedName("id") val id : Int,
//    @SerializedName("imdb_id") val imdb_id : String,
//    @SerializedName("original_language") val original_language : String,
//    @SerializedName("original_title") val original_title : String,
    @SerializedName("overview") val overview : String,
//    @SerializedName("popularity") val popularity : Double,
    @SerializedName("poster_path") val posterPath : String,
//    @SerializedName("production_companies") val production_companies : List<Production_companies>,
//    @SerializedName("production_countries") val production_countries : List<Production_countries>,
    @SerializedName("release_date") val releaseDate : String,
//    @SerializedName("revenue") val revenue : Int,
    @SerializedName("runtime") val runtime : Int,
//    @SerializedName("spoken_languages") val spoken_languages : List<Spoken_languages>,
//    @SerializedName("status") val status : String,
//    @SerializedName("tagline") val tagline : String,
    @SerializedName("title") val title : String
//    @SerializedName("video") val video : Boolean,
//    @SerializedName("vote_average") val vote_average : Double,
//    @SerializedName("vote_count") val vote_count : Int
)  {
    fun releaseYear():String {
        val readFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val humanFormat = DateTimeFormatter.ofPattern("yyyy")
        val date = LocalDate.parse(releaseDate, readFormat)
        return humanFormat.format(date)
    }
}

data class Genres (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)