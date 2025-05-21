package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.application.port.out.GetWebtoonEpisodeQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface QueryDslWebtoonEpisodeRepository {
    fun find(query: GetWebtoonEpisodeQuery): WebtoonEpisodeJpaEntity?
    fun findAll(query: GetWebtoonEpisodeQuery, pageable: Pageable): Page<WebtoonEpisodeJpaEntity>
}