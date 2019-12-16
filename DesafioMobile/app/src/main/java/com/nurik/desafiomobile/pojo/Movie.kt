package com.nurik.desafiomobile.pojo

import java.io.Serializable

class Movie (val id: Int,
             val original_title : String,
             val release_date: String,
             var poster_path: String,
             val backdrop_path: String,
             val genre_ids: List<Int>?,
             val runtime: Int?,
             val overview: String?,
             val credits: Credit?): Serializable{
}