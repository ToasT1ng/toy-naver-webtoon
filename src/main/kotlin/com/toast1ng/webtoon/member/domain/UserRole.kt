package com.toast1ng.webtoon.member.domain

import org.springframework.security.core.GrantedAuthority

enum class UserRole : GrantedAuthority {
    USER, CREATOR, ADMIN;

    override fun getAuthority(): String = name
}