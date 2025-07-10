package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.UpdateWebtoonLikeCountUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.UpdateWebtoonLikeCountCommand
import com.toast1ng.webtoon.product.application.port.out.UpdateWebtoonPort
import org.springframework.stereotype.Service

@Service
class WebtoonProductCommandService(
    private val updateWebtoonPort: UpdateWebtoonPort,
) : UpdateWebtoonLikeCountUseCase {
    override fun updateLikeCount(command: UpdateWebtoonLikeCountCommand) {
        val webtoon = command.webtoon
        when (command.likeType) {
            UpdateWebtoonLikeCountCommand.LikeType.ADD -> webtoon.increaseLikeCount()
            UpdateWebtoonLikeCountCommand.LikeType.MINUS -> webtoon.decreaseLikeCount()
        }
        updateWebtoonPort.updateWebtoon(webtoon)
    }
}