package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.member.adapter.out.persistence.entity.QUserJpaEntity.userJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonEpisodeCommentJpaEntity.webtoonEpisodeCommentJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeCommentJpaEntity
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeCommentQuery

class QueryDslWebtoonEpisodeCommentRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QueryDslWebtoonEpisodeCommentRepository {

    override fun findAll(query: WebtoonEpisodeCommentQuery): List<WebtoonEpisodeCommentJpaEntity> {
        return queryFactory
            .select(webtoonEpisodeCommentJpaEntity)
            .from(webtoonEpisodeCommentJpaEntity)
            .leftJoin(webtoonEpisodeCommentJpaEntity.webtoonUser(), userJpaEntity).fetchJoin()
            .where(makeBooleanExpression(query))
            .fetch()
    }

    private fun makeBooleanExpression(query: WebtoonEpisodeCommentQuery): BooleanBuilder {
        val builder = BooleanBuilder()
        query.webtoonEpisodeId?.let { builder.and(webtoonEpisodeCommentJpaEntity.webtoonEpisodeId.eq(it)) }
        query.parentId?.let { builder.and(webtoonEpisodeCommentJpaEntity.parentId.eq(it)) }
        query.userId?.let { builder.and(webtoonEpisodeCommentJpaEntity.webtoonUser().id.eq(it)) }
        return builder
    }
}