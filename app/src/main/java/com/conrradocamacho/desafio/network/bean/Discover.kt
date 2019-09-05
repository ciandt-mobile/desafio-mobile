package com.conrradocamacho.desafio.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Conrrado Camacho on 03/09/2019.
 * con.webmaster@gmail.com
 */
class Discover (
    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    val results: List<Movie>?
): Serializable