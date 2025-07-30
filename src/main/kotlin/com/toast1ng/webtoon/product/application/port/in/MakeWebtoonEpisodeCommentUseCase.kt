package com.toast1ng.webtoon.product.application.port.`in`

import com.toast1ng.webtoon.product.application.port.`in`.command.MakeWebtoonEpisodeCommentCommand

interface MakeWebtoonEpisodeCommentUseCase {
    fun makeComment(command: MakeWebtoonEpisodeCommentCommand)
}