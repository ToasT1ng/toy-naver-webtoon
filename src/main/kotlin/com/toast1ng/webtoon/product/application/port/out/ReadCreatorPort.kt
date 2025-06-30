package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.Creator

interface ReadCreatorPort {
    fun getCreatorsByWebtoonIds(webtoonIds: List<Long>): List<Creator>
}