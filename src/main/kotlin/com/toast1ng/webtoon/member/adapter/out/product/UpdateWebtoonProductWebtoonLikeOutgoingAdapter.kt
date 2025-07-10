package com.toast1ng.webtoon.member.adapter.out.product

import com.toast1ng.webtoon.common.domain.annotations.OutgoingAdapter
import com.toast1ng.webtoon.member.application.port.out.ChangeWebtoonLikeCountPort
import com.toast1ng.webtoon.product.adapter.`in`.internal.UpdateWebtoonProductInternalAdapter
import com.toast1ng.webtoon.product.domain.WebtoonProduct

@OutgoingAdapter
class UpdateWebtoonProductWebtoonLikeOutgoingAdapter(
    private val updateWebtoonProductInternalAdapter: UpdateWebtoonProductInternalAdapter,
) : ChangeWebtoonLikeCountPort {

    override fun addLikeCount(webtoon: WebtoonProduct) {
        updateWebtoonProductInternalAdapter.addLikeCount(webtoon)
    }

    override fun minusLikeCount(webtoon: WebtoonProduct) {
        updateWebtoonProductInternalAdapter.minusLikeCount(webtoon)
    }
}