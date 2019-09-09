package com.conrradocamacho.desafio.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Conrrado Camacho on 08/09/2019.
 * con.webmaster@gmail.com
 */
class Detail(
    val id: Int,
    val title: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val runtime: Int?,
    val genres: List<Item>?,
    val overview: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?
): Serializable