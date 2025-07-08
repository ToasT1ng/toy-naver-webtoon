package com.toast1ng.webtoon.member.adapter.out.persistence.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.member.adapter.out.persistence.entity.QUserLikedWebtoonJpaEntity.userLikedWebtoonJpaEntity
import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserLikedWebtoonJpaEntity
import com.toast1ng.webtoon.member.application.port.out.query.UserLikedWebtoonQuery

class QueryDslUserLikedWebtoonRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QueryDslUserLikedWebtoonRepository {
    override fun find(query: UserLikedWebtoonQuery): UserLikedWebtoonJpaEntity? {
        return queryFactory
            .selectFrom(userLikedWebtoonJpaEntity)
            .where(
                makeBooleanExpression(query)
            )
            .fetchOne()
    }

    override fun findAll(query: UserLikedWebtoonQuery): List<UserLikedWebtoonJpaEntity> {
        return queryFactory
            .selectFrom(userLikedWebtoonJpaEntity)
            .where(
                makeBooleanExpression(query)
            )
            .fetch()
    }

    private fun makeBooleanExpression(query: UserLikedWebtoonQuery): BooleanBuilder {
        val builder = BooleanBuilder()
        query.userId?.let { builder.and(userLikedWebtoonJpaEntity.user().id.eq(it)) }
        query.webtoonId?.let { builder.and(userLikedWebtoonJpaEntity.webtoon().id.eq(it)) }
        query.status?.let { builder.and(userLikedWebtoonJpaEntity.status.eq(it)) }
        return builder
    }
}