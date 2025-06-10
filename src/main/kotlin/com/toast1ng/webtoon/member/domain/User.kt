@file:Suppress("INAPPLICABLE_TARGET_ON_PROPERTY_WARNING")

package com.toast1ng.webtoon.member.domain
import org.springframework.security.core.userdetails.UserDetails

data class User(
    val id: Long = 0L,
    private val username: String,
    private val password: String,
    val role: UserRole,
) : UserDetails {
    override fun getAuthorities() = listOf(this.role)

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}

object UserFactory {
    fun create(username: String, password: String, role: UserRole): User {
        return User(
            username = username,
            password = password,
            role = role
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