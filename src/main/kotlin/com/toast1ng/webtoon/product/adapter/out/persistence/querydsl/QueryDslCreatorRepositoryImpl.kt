package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.CreatorJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QCreatorJpaEntity.creatorJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QPersonJpaEntity.personJpaEntity

class QueryDslCreatorRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QueryDslCreatorRepository {
    override fun findAllByWebtoonIds(webtoonIds: List<Long>): List<CreatorJpaEntity> {
        return queryFactory
            .selectFrom(creatorJpaEntity)
            .join(creatorJpaEntity.person(), personJpaEntity)
            .where(creatorJpaEntity.webtoon().id.`in`(webtoonIds))
            .fetch()
    }

}
