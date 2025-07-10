package com.toast1ng.webtoon.product.adapter.`in`.internal

import com.toast1ng.webtoon.common.domain.annotations.InternalAdapter
import com.toast1ng.webtoon.product.application.port.`in`.command.UpdateWebtoonLikeCountCommand
import com.toast1ng.webtoon.product.application.port.`in`.UpdateWebtoonLikeCountUseCase
import com.toast1ng.webtoon.product.domain.WebtoonProduct

@InternalAdapter
class UpdateWebtoonProductInternalAdapter(
    private val updateWebtoonLikeCountUseCase: UpdateWebtoonLikeCountUseCase,
) {
    fun addLikeCount(webtoon: WebtoonProduct) {
        updateWebtoonLikeCountUseCase.updateLikeCount(
            UpdateWebtoonLikeCountCommand(
                webtoon = webtoon,
                likeType = UpdateWebtoonLikeCountCommand.LikeType.ADD
            )
        )
    }

    fun minusLikeCount(webtoon: WebtoonProduct) {
        updateWebtoonLikeCountUseCase.updateLikeCount(
            UpdateWebtoonLikeCountCommand(
                webtoon = webtoon,
                likeType = UpdateWebtoonLikeCountCommand.LikeType.MINUS
            )
        )
    }
}