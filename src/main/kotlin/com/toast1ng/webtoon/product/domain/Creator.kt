package com.toast1ng.webtoon.product.domain

data class Creator(
    val personId: Long,
    val webtoonId: Long,
    val name: String,
    val role: CreatorRole
)