package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.adapter.out.persistence.UserPersistenceAdapter
import com.toast1ng.webtoon.member.application.port.`in`.SpringSecurityWebtoonUserDetailUseCase
import com.toast1ng.webtoon.member.application.port.out.query.UserQuery
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val springDataUserRepository: UserPersistenceAdapter
) : SpringSecurityWebtoonUserDetailUseCase {
    override fun loadUserByUsername(username: String): UserDetails {
        return springDataUserRepository.getUser(UserQuery(id = username.toLong()))
            ?: throw UsernameNotFoundException("User not found")
    }
}