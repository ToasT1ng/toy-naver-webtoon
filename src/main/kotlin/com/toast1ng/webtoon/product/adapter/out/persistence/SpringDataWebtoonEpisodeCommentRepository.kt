package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeCommentJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.querydsl.QueryDslWebtoonEpisodeCommentRepository
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataWebtoonEpisodeCommentRepository : JpaRepository<WebtoonEpisodeCommentJpaEntity, Long>, QueryDslWebtoonEpisodeCommentRepository {
}