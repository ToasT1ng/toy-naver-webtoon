package com.toast1ng.webtoon.member.adapter.`in`.web.response

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val username: String,
    val nickname: String? = null,
    val profileImageUrl: String? = null
)