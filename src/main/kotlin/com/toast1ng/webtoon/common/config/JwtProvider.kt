package com.toast1ng.webtoon.common.config

import com.toast1ng.webtoon.common.utils.toDate
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime

@Component
class JwtProvider(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration-min}") private val expirationMinute: Long
) {
    private val key: Key by lazy {
        Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun createToken(username: String): String {
        val now = LocalDateTime.now()
        val expiration = now.plusMinutes(expirationMinute)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now.toDate())
            .setExpiration(expiration.toDate())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getUsername(token: String): String? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
                .subject
        } catch (e: Exception) {
            null
        }
    }

    fun isValid(token: String): Boolean = getUsername(token) != null
}