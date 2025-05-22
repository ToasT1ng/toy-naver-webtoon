package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface QueryDslWebtoonEpisodeRepository {
    fun find(query: WebtoonEpisodeQuery): WebtoonEpisodeJpaEntity?
    fun findAll(query: WebtoonEpisodeQuery, pageable: Pageable): Page<WebtoonEpisodeJpaEntity>
}