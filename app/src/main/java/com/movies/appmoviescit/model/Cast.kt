package com.movies.appmoviescit.model

import java.io.Serializable

data class Cast(val id: Long,
                val character: String,
                val name: String,
                val profile_path: String): Serializable