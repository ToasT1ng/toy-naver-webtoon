package com.toast1ng.webtoon.common.config

import com.toast1ng.webtoon.common.utils.toDate
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime
import java.util.UUID

@Component
class JwtProvider(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.access-token.expiration-min}") private val accessTokenExpirationMinute: Long,
    @Value("\${jwt.refresh-token.default-expiration-min}") private val defaultRefreshTokenExpirationMinute: Long,
    @Value("\${jwt.refresh-token.auto-login-expiration-min}") private val autoLoginRefreshTokenExpirationMinute: Long,
) {
    private val key: Key by lazy {
        Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun createAccessToken(username: String): String {
        val now = LocalDateTime.now()
        val expiration = now.plusMinutes(accessTokenExpirationMinute)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now.toDate())
            .setExpiration(expiration.toDate())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun createRefreshToken(username: String, isAutoLogin: Boolean): String {
        val now = LocalDateTime.now()
        val expiration = now.plusMinutes(getRefreshTokenExpirationMinute(isAutoLogin))

        return Jwts.builder()
            .setSubject(username)
            .claim("type", "refresh")
            .claim("tokenId", UUID.randomUUID().toString())
            .setIssuedAt(now.toDate())
            .setExpiration(expiration.toDate())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getRefreshTokenExpirationMinute(isAutoLogin: Boolean): Long =
        if (isAutoLogin) autoLoginRefreshTokenExpirationMinute else defaultRefreshTokenExpirationMinute

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