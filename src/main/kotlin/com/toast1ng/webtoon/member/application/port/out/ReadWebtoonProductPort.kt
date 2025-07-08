package com.toast1ng.webtoon.member.application.port.out

import com.toast1ng.webtoon.product.domain.WebtoonProduct

interface ReadWebtoonProductPort {
    fun findWebtoonProductById(webtoonProductId: Long): WebtoonProduct?
}