package com.toast1ng.webtoon.member.adapter.out.persistence.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.member.adapter.out.persistence.entity.QUserJpaEntity.userJpaEntity
import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserJpaEntity
import com.toast1ng.webtoon.member.application.port.out.query.UserQuery

class QueryDslUserRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QueryDslUserRepository {
    override fun find(query: UserQuery): UserJpaEntity? {
        return queryFactory
            .selectFrom(userJpaEntity)
            .where(
                makeBooleanExpression(query)
            )
            .fetchOne()
    }

    override fun findFirst(query: UserQuery): UserJpaEntity? {
        return queryFactory
            .selectFrom(userJpaEntity)
            .where(
                makeBooleanExpression(query)
            )
            .fetchFirst()
    }

    override fun exists(query: UserQuery): Boolean {
        return findFirst(query) != null
    }

    private fun makeBooleanExpression(query: UserQuery): BooleanBuilder {
        val builder = BooleanBuilder()
        query.id?.let { builder.and(userJpaEntity.id.eq(it)) }
        query.username?.let { builder.and(userJpaEntity.username.eq(it)) }
        return builder
    }
}