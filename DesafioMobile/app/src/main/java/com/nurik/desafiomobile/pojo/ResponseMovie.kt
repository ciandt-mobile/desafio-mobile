package com.nurik.desafiomobile.pojo

class ResponseMovie(val page: Int,
                    val total_results: Int,
                    val total_pages: Int,
                    val results: List<Movie>)
