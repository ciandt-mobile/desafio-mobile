package com.tartagliaeg.tmdb.tools

object KeyTools {
    fun compose(vararg keys: String): String {
        return keys.reduce { acc, s -> "$acc::$s" }
    }

    fun decompose(key: String): List<String> {
        return key.split("::")
    }
}
