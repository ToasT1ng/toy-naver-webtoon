package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeNavigationDto
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeNavigationQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodePagingQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery
import org.springframework.data.domain.Page

interface QueryDslWebtoonEpisodeRepository {
    fun find(query: WebtoonEpisodeQuery): WebtoonEpisodeJpaEntity?
    fun findAll(query: WebtoonEpisodePagingQuery): Page<WebtoonEpisodeJpaEntity>
    fun findNavigationIdsByEpisodeNumber(query: WebtoonEpisodeNavigationQuery): WebtoonEpisodeNavigationDto
}