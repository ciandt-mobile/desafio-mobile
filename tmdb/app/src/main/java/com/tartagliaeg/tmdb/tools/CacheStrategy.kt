package com.tartagliaeg.tmdb.tools




class SimpleMemoryCache(private val domain: String) {
    private val cache: MutableMap<String, Any> = HashMap()

    private fun composeKey(key: String): String { return KeyTools.compose(domain, key) }

    fun set(key: String, value: Any) { cache[composeKey(key)] = value }
    fun <T>get(key: String): T { return (cache[composeKey(key)] as T)!! }
    fun has(key: String): Boolean { return cache.containsKey(composeKey(key)) }

}