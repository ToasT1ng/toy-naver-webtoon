package com.toast1ng.webtoon.product.application.port.out

import com.toast1ng.webtoon.product.domain.WebtoonEpisodeComment

interface MakeWebtoonEpisodeCommentPort {
    fun saveComment(comment: WebtoonEpisodeComment)
}