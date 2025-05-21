package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.querydsl.QueryDslWebtoonEpisodeRepository
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataWebtoonEpisodeRepository : JpaRepository<WebtoonEpisodeJpaEntity, Long>, QueryDslWebtoonEpisodeRepository {
}