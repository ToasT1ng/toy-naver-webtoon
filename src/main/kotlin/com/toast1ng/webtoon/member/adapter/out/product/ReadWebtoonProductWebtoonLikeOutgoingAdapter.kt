package com.toast1ng.webtoon.member.adapter.out.product

import com.toast1ng.webtoon.common.domain.annotations.OutgoingAdapter
import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonProductPort
import com.toast1ng.webtoon.product.adapter.`in`.internal.ReadWebtoonProductInternalAdapter
import com.toast1ng.webtoon.product.domain.WebtoonProduct

@OutgoingAdapter
class ReadWebtoonProductWebtoonLikeOutgoingAdapter(
    private val readWebtoonProductInternalAdapter: ReadWebtoonProductInternalAdapter,
) : ReadWebtoonProductPort {
    override fun findWebtoonProductById(webtoonProductId: Long): WebtoonProduct? {
        return readWebtoonProductInternalAdapter.getWebtoon(webtoonProductId)
    }
}