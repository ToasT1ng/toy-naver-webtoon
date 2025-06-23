package com.toast1ng.webtoon.common.config

import com.toast1ng.webtoon.common.properties.JwtProperties
import com.toast1ng.webtoon.common.utils.toDate
import com.toast1ng.webtoon.common.utils.toLocalDateTime
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
) {
    private val key: Key by lazy {
        Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray())
    }

    fun createAccessToken(id: Long): String {
        val now = LocalDateTime.now()
        val expiration = now.plusMinutes(jwtProperties.accessToken.expirationMin)

        return Jwts.builder()
            .setSubject(id.toString())
            .setIssuedAt(now.toDate())
            .setExpiration(expiration.toDate())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun createRefreshToken(id: Long, isAutoLogin: Boolean): String {
        val now = LocalDateTime.now()
        val expiration = now.plusMinutes(getRefreshTokenExpirationMinute(isAutoLogin))

        return Jwts.builder()
            .setSubject(id.toString())
            .claim("type", "refresh")
            .claim("tokenId", UUID.randomUUID().toString())
            .setIssuedAt(now.toDate())
            .setExpiration(expiration.toDate())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getRefreshTokenExpirationMinute(isAutoLogin: Boolean): Long {
        return if (isAutoLogin) jwtProperties.refreshToken.autoLoginExpirationMin
        else jwtProperties.refreshToken.defaultExpirationMin
    }

    fun getUserId(token: String): Long? {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject?.toLong()
    }

    fun getExpiration(token: String): LocalDateTime? {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .expiration?.toLocalDateTime()
    }

    fun getRemainingSeconds(token: String): Long? {
        val exp = getExpiration(token) ?: return null
        return Duration.between(LocalDateTime.now(), exp).seconds.coerceAtLeast(0)
    }

    fun isValid(token: String): Boolean = getUserId(token) != null
}