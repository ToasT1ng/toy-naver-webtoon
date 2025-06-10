package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.common.domain.QuerySortOption
import com.toast1ng.webtoon.common.domain.SortDirection
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QCreatorJpaEntity.creatorJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QGenreJpaEntity.genreJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QPersonJpaEntity.personJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonProductJpaEntity.webtoonProductJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonProductJpaEntity
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductSortColumn
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductSortQuery

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

    override fun findAll(query: WebtoonProductSortQuery): List<WebtoonProductJpaEntity> {
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
            .orderBy(*applySort(query.sortOptions).toTypedArray())
            .fetch()
    }

    private fun applySort(sortOptions: List<QuerySortOption<WebtoonProductSortColumn>>): List<OrderSpecifier<*>> {
        return sortOptions.mapNotNull { option ->
            val expression = when (option.key) {
                WebtoonProductSortColumn.RATING -> webtoonProductJpaEntity.rating
                WebtoonProductSortColumn.VIEWS -> webtoonProductJpaEntity.views
            }
            when (option.direction) {
                SortDirection.ASC -> expression.asc()
                SortDirection.DESC -> expression.desc()
            }
        }
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