package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.WebtoonProduct

interface UpdateWebtoonPort {
    fun updateWebtoon(webtoon: WebtoonProduct)
    fun deleteWebtoon(webtoonId: Long)
}