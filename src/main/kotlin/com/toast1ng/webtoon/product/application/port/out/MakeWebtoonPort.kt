package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.WebtoonProduct

interface MakeWebtoonPort {
    fun makeWebtoon(webtoonProduct: WebtoonProduct)
}