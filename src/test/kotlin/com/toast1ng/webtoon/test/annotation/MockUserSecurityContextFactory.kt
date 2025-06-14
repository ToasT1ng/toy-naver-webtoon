package com.toast1ng.webtoon.test.annotation

import com.toast1ng.webtoon.member.domain.UserFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory

class MockUserSecurityContextFactory : WithSecurityContextFactory<WithMyMockUser> {
    override fun createSecurityContext(annotation: WithMyMockUser): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()
        val userDetails = UserFactory.create(username = annotation.username, annotation.password)
        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        context.authentication = authentication
        return context
    }
}