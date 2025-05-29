package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.common.QuerySortOption
import com.toast1ng.webtoon.common.SortDirection
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonEpisodeJpaEntity.webtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonImageJpaEntity.webtoonImageJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonProductJpaEntity.webtoonProductJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeNavigationDto
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeNavigationQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodePagingQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeSortColumn
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class QueryDslWebtoonEpisodeRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QueryDslWebtoonEpisodeRepository {
    override fun find(query: WebtoonEpisodeQuery): WebtoonEpisodeJpaEntity? {
        return queryFactory
            .selectDistinct(
                webtoonEpisodeJpaEntity,
            )
            .from(webtoonEpisodeJpaEntity)
            .leftJoin(webtoonEpisodeJpaEntity.webtoonImages, webtoonImageJpaEntity).fetchJoin()
            .leftJoin(webtoonEpisodeJpaEntity.webtoon(), webtoonProductJpaEntity).fetchJoin()
            .where(
                makeBooleanExpression(query),
            )
            .fetchOne()
    }

    private fun makeBooleanExpression(query: WebtoonEpisodeQuery): BooleanBuilder {
        val builder = BooleanBuilder()
        query.id?.let { builder.and(webtoonEpisodeJpaEntity.id.eq(it)) }
        query.webtoonId?.let { builder.and(webtoonEpisodeJpaEntity.webtoon().id.eq(it)) }
        return builder
    }


    override fun findAll(query: WebtoonEpisodePagingQuery): Page<WebtoonEpisodeJpaEntity> {
        val pageable = PageRequest.of(query.pageNumber, query.pageSize)

        val content = queryFactory
            .select(webtoonEpisodeJpaEntity)
            .from(webtoonEpisodeJpaEntity)
            .leftJoin(webtoonEpisodeJpaEntity.webtoon(), webtoonProductJpaEntity).fetchJoin()
            .where(makeBooleanExpression(query))
            .orderBy(*applySort(query.sortOptions).toTypedArray())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val total = count(query)

        return PageImpl(content, pageable, total)
    }

    private fun count(query: WebtoonEpisodePagingQuery): Long {
        return queryFactory
            .select(webtoonEpisodeJpaEntity.count())
            .from(webtoonEpisodeJpaEntity)
            .leftJoin(webtoonEpisodeJpaEntity.webtoon(), webtoonProductJpaEntity)
            .where(makeBooleanExpression(query))
            .fetchOne() ?: 0L
    }

    private fun makeBooleanExpression(query: WebtoonEpisodePagingQuery): BooleanBuilder {
        val builder = BooleanBuilder()
        query.id?.let { builder.and(webtoonEpisodeJpaEntity.id.eq(it)) }
        query.webtoonId?.let { builder.and(webtoonEpisodeJpaEntity.webtoon().id.eq(it)) }
        query.uploadDateTo?.let { builder.and(webtoonEpisodeJpaEntity.uploadAt.loe(it)) }
        return builder
    }

    private fun applySort(sortOptions: List<QuerySortOption<WebtoonEpisodeSortColumn>>): List<OrderSpecifier<*>> {
        return sortOptions.map { option ->
            when (option.key) {
                WebtoonEpisodeSortColumn.UPLOAD_DATE -> {
                    if (option.direction == SortDirection.ASC) {
                        webtoonEpisodeJpaEntity.uploadAt.asc()
                    } else {
                        webtoonEpisodeJpaEntity.uploadAt.desc()
                    }
                }
            }
        }
    }

    override fun findNavigationIdsByEpisodeNumber(
        query: WebtoonEpisodeNavigationQuery,
    ): WebtoonEpisodeNavigationDto {
        val currentEpisodeNumber = queryFactory
            .select(webtoonEpisodeJpaEntity.episodeNumber)
            .from(webtoonEpisodeJpaEntity)
            .where(
                webtoonEpisodeJpaEntity.id.eq(query.currentEpisodeId),
                webtoonEpisodeJpaEntity.webtoon().id.eq(query.webtoonId),
            )
            .fetchOne() ?: throw IllegalArgumentException("Episode not found")

        val previousEpisodeId = queryFactory
            .select(webtoonEpisodeJpaEntity.id)
            .from(webtoonEpisodeJpaEntity)
            .where(
                webtoonEpisodeJpaEntity.uploadAt.loe(query.uploadDateTo),
                webtoonEpisodeJpaEntity.webtoon().id.eq(query.webtoonId),
                webtoonEpisodeJpaEntity.episodeNumber.lt(currentEpisodeNumber),
            )
            .orderBy(webtoonEpisodeJpaEntity.id.desc())
            .fetchFirst()

        val nextEpisodeId = queryFactory
            .select(webtoonEpisodeJpaEntity.id)
            .from(webtoonEpisodeJpaEntity)
            .where(
                webtoonEpisodeJpaEntity.uploadAt.loe(query.uploadDateTo),
                webtoonEpisodeJpaEntity.webtoon().id.eq(query.webtoonId),
                webtoonEpisodeJpaEntity.episodeNumber.gt(currentEpisodeNumber),
            )
            .orderBy(webtoonEpisodeJpaEntity.id.asc())
            .fetchFirst()

        return WebtoonEpisodeNavigationDto(
            previousEpisodeId = previousEpisodeId,
            nextEpisodeId = nextEpisodeId,
        )
    }
}