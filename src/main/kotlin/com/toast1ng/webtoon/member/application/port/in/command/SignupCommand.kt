package com.toast1ng.webtoon.member.application.port.`in`.command

import com.toast1ng.webtoon.member.domain.UserRole

data class SignupCommand(
    val username: String,
    val password: String,
    val role: UserRole
)