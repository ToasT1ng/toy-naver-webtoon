package com.toast1ng.webtoon.member.application.port.out

import com.toast1ng.webtoon.product.domain.WebtoonProduct

interface ChangeWebtoonLikeCountPort {
    fun addLikeCount(webtoon: WebtoonProduct)
    fun minusLikeCount(webtoon: WebtoonProduct)
}