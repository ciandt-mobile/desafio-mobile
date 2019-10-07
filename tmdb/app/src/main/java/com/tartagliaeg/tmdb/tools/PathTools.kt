package com.tartagliaeg.tmdb.tools


object UriTools {
    fun join(vararg segments: String?): String {

        return segments
            .filter { it != null && it.isNotEmpty() }
            .joinToString("/") { removeInitialCharacter(removeFinalCharacter(it!!, "/"), "/") }
    }

    private fun removeFinalCharacter(segment: String, char: String): String {
        var iteration = segment
        while (iteration.endsWith(char))
            iteration = iteration.substring(0, iteration.length - 1)
        return iteration
    }

    private fun removeInitialCharacter(segment: String, char: String): String {
        var iteration = segment
        while (iteration.startsWith(char))
            iteration = iteration.substring(1)
        return iteration
    }
}

