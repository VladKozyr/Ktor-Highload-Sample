package com.kozyr.vlad.service

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import redis.clients.jedis.Jedis
import redis.clients.jedis.params.SetParams

private const val CACHE_TIMEOUT = 300L

class CacheService(
    private val jedis: Jedis
) {

    internal inline fun <reified T> getByIndex(index: String, generator: () -> T?): T? {
        return try {
            Json.decodeFromString<T>(jedis.get(index))
        } catch (e: Exception) {
            generator()?.let {
                jedis.set(index, Json.encodeToString(it), SetParams().ex(CACHE_TIMEOUT))
                it
            }
        }
    }
}