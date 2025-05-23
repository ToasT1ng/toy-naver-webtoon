package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QCreatorJpaEntity.creatorJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QGenreJpaEntity.genreJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QPersonJpaEntity.personJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonProductJpaEntity.webtoonProductJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonProductJpaEntity
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductQuery

class QueryDslWebtoonProductRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QueryDslWebtoonProductRepository {
    override fun find(query: WebtoonProductQuery): WebtoonProductJpaEntity? {
        return queryFactory
            .selectDistinct(
                webtoonProductJpaEntity
            )
            .from(webtoonProductJpaEntity)
            .join(webtoonProductJpaEntity.creators, creatorJpaEntity).fetchJoin()
            .join(creatorJpaEntity.person(), personJpaEntity).fetchJoin()
            .join(webtoonProductJpaEntity.genre(), genreJpaEntity).fetchJoin()
            .where(
                query.id?.let { webtoonProductJpaEntity.id.eq(query.id) },
                query.day?.let { webtoonProductJpaEntity.day.eq(query.day.value) },
            )
            .fetchOne()
    }

    override fun findAll(query: WebtoonProductQuery): List<WebtoonProductJpaEntity> {
        return queryFactory
            .selectDistinct(
                webtoonProductJpaEntity
            )
            .from(webtoonProductJpaEntity)
            .join(webtoonProductJpaEntity.creators, creatorJpaEntity).fetchJoin()
            .join(creatorJpaEntity.person(), personJpaEntity).fetchJoin()
            .join(webtoonProductJpaEntity.genre(), genreJpaEntity).fetchJoin()
            .where(
                query.day?.let { webtoonProductJpaEntity.day.eq(query.day.value) },
            )
            .fetch()
    }

    override fun count(query: WebtoonProductQuery): Long {
        return queryFactory
            .select(webtoonProductJpaEntity.countDistinct())
            .from(webtoonProductJpaEntity)
            .join(webtoonProductJpaEntity.creators, creatorJpaEntity)
            .join(creatorJpaEntity.person(), personJpaEntity)
            .join(webtoonProductJpaEntity.genre(), genreJpaEntity)
            .where(
                query.id?.let { webtoonProductJpaEntity.id.eq(query.id) },
                query.day?.let { webtoonProductJpaEntity.day.eq(query.day.value) }
            )
            .fetchOne() ?: 0L
    }
}