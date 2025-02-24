package com.toast1ng.webtoon.common

import kotlin.annotation.AnnotationTarget
import kotlin.annotation.AnnotationRetention
import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class PersistenceAdapter(
    @get:AliasFor(annotation = Component::class)
    val value: String = ""
)
