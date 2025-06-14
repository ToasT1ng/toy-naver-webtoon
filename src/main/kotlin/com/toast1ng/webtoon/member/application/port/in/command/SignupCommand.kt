package com.toast1ng.webtoon.member.application.port.`in`.command

data class SignupCommand(
    val username: String,
    val password: String,
    val confirmPassword: String,
)