package com.toast1ng.webtoon.product.domain

enum class CreatorRole(val value: String) {
    WRITER("WRITER"), ILLUSTRATOR("ILLUST"), ORIGINAL_AUTHOR("ORIGINAL");

    companion object {
        fun fromValue(value: String): CreatorRole {
            return CreatorRole.entries.firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("Invalid CreatorRole value: $value")
        }
    }
}
