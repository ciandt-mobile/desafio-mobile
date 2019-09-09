package com.conrradocamacho.desafio.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Conrrado Camacho on 08/09/2019.
 * con.webmaster@gmail.com
 */
class Cast(
    @SerializedName("cast_id")
    val castId: Int,

    val character: String,

    val name: String,

    @SerializedName("profile_path")
    val profilePath: String?
): Serializable