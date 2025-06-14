package com.toast1ng.webtoon.test.annotation

import org.springframework.security.test.context.support.WithSecurityContext

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = MockUserSecurityContextFactory::class)
annotation class WithMyMockUser (
    val username: String = "mockuser",
    val password: String = "password"
)