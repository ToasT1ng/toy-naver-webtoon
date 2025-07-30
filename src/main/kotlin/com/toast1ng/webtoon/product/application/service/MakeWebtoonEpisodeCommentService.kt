package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.command.MakeWebtoonEpisodeCommentCommand
import com.toast1ng.webtoon.product.application.port.`in`.MakeWebtoonEpisodeCommentUseCase
import com.toast1ng.webtoon.product.application.port.out.MakeWebtoonEpisodeCommentPort
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeCommentFactory
import org.springframework.stereotype.Service

@Service
class MakeWebtoonEpisodeCommentService(
    private val makeWebtoonEpisodeCommentPort: MakeWebtoonEpisodeCommentPort
) : MakeWebtoonEpisodeCommentUseCase {
    override fun makeComment(command: MakeWebtoonEpisodeCommentCommand) {
        makeWebtoonEpisodeCommentPort.saveComment(
            WebtoonEpisodeCommentFactory.create(
                parentId = command.parentId ?: 0L,
                episodeId = command.episodeId,
                content = command.content,
                user = command.user
            )
        )
    }
}