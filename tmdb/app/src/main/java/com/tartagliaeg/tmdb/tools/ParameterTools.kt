package com.tartagliaeg.tmdb.tools


object ParameterTools {
    fun <T>firstNotEqualsTo(notEq: T, vararg params: T): T {
        for(p in params)
            if(notEq != p)
                return p

        return notEq
    }
}

