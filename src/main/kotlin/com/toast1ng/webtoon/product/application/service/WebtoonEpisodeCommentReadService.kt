package com.toast1ng.webtoon.product.application.service

import com.toast1ng.webtoon.product.application.port.`in`.GetWebtoonEpisodeCommentUseCase
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonEpisodeCommentPort
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeComment
import org.springframework.stereotype.Service

@Service
class WebtoonEpisodeCommentReadService(
    private val readWebtoonEpisodeCommentPort: ReadWebtoonEpisodeCommentPort,
) : GetWebtoonEpisodeCommentUseCase {

    override fun getTopCommentsByEpisodeId(episodeId: Long): List<WebtoonEpisodeComment> {
        return readWebtoonEpisodeCommentPort.findTopCommentsByEpisodeId(episodeId)
    }

    override fun getChildCommentsByParentId(parentId: Long): List<WebtoonEpisodeComment> {
        return readWebtoonEpisodeCommentPort.findChildCommentsByParentId(parentId)
    }
}