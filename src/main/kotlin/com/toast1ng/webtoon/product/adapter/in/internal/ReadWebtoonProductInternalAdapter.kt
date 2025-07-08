package com.toast1ng.webtoon.product.adapter.`in`.internal

import com.toast1ng.webtoon.common.domain.annotations.InternalAdapter
import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonUseCase
import com.toast1ng.webtoon.product.domain.WebtoonProduct

@InternalAdapter
class ReadWebtoonProductInternalAdapter(
    private val getWebtoonUseCase: GetWebtoonUseCase,
) {
    fun getWebtoon(webtoonId: Long): WebtoonProduct {
        return getWebtoonUseCase.getWebtoon(webtoonId)
    }
}