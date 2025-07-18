package com.toast1ng.webtoon.member.domain

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class User(
    val id: Long = 0L,
    private val username: String,
    private val password: String,
    val role: UserRole,
    val nickname: String? = null, //TODO 닉네임 not null (default 설정 in UserFactory)
    val profileImage: String? = null,
) : UserDetails {
    override fun getAuthorities() = listOf(SimpleGrantedAuthority("ROLE_$role"))

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}

object UserFactory {
    fun create(username: String, password: String): User {
        return User(
            username = username,
            password = password,
            role = UserRole.USER
        )
    }

    fun createWithId(id: Long, username: String, password: String, role: UserRole): User {
        return User(
            id = id,
            username = username,
            password = password,
            role = role
        )
    }
}