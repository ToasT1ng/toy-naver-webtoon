package com.toast1ng.webtoon.test.utils

import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.reflect.full.memberProperties

fun Any.toQueryString(): String {
    return this::class.memberProperties
        .mapNotNull { prop ->
            val value = prop.getter.call(this)
            if (value != null) {
                val encodedKey = URLEncoder.encode(prop.name, StandardCharsets.UTF_8.name())
                val encodedValue = URLEncoder.encode(value.toString(), StandardCharsets.UTF_8.name())
                "$encodedKey=$encodedValue"
            } else {
                null
            }
        }
        .joinToString("&")
}