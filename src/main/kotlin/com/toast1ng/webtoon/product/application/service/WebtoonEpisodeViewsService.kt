package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.UpdateWebtoonViewUseCase
import com.toast1ng.webtoon.product.application.port.`in`.command.UpdateWebtoonViewsCommand
import org.springframework.stereotype.Service

@Service
class WebtoonEpisodeViewsService : UpdateWebtoonViewUseCase {

    override fun updateWebtoonViews(command: UpdateWebtoonViewsCommand) {
        //TODO: redis를 사용하여 조회수 업데이트
    }
}