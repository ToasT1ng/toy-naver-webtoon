package com.toast1ng.webtoon.member.adapter.`in`.web.request

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank
    val username: String,
    @field:NotBlank
    val password: String
)