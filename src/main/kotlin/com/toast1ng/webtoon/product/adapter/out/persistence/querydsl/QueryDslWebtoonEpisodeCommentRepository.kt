package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeCommentJpaEntity
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeCommentQuery


interface QueryDslWebtoonEpisodeCommentRepository {
    fun findAll(query: WebtoonEpisodeCommentQuery): List<WebtoonEpisodeCommentJpaEntity>
}