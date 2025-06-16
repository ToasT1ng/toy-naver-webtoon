package com.toast1ng.webtoon.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    var secret: String,
    var accessToken: AccessToken,
    var refreshToken: RefreshToken
) {
    data class AccessToken(
        var expirationMin: Long
    )

    data class RefreshToken(
        var defaultExpirationMin: Long,
        var autoLoginExpirationMin: Long
    )
}