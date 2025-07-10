package com.toast1ng.webtoon.product.application.port.`in`.command

import com.toast1ng.webtoon.product.domain.WebtoonProduct

data class UpdateWebtoonLikeCountCommand(
    val webtoon: WebtoonProduct,
    val likeType: LikeType
) {
    enum class LikeType {
        ADD, MINUS
    }
}