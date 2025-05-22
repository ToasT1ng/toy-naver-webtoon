package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonEpisodeJpaEntity.webtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonImageJpaEntity.webtoonImageJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonProductJpaEntity.webtoonProductJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

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

    override fun findAll(query: WebtoonEpisodeQuery, pageable: Pageable): Page<WebtoonEpisodeJpaEntity> {
        val content = queryFactory
            .selectDistinct(
                webtoonEpisodeJpaEntity,
            )
            .from(webtoonEpisodeJpaEntity)
            .leftJoin(webtoonEpisodeJpaEntity.webtoonImages, webtoonImageJpaEntity).fetchJoin()
            .leftJoin(webtoonEpisodeJpaEntity.webtoon(), webtoonProductJpaEntity).fetchJoin()
            .where(
                makeBooleanExpression(query),
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val total = count(query)

        return PageImpl(content, pageable, total)
    }

    private fun count(query: WebtoonEpisodeQuery): Long {
        return queryFactory
            .select(webtoonEpisodeJpaEntity.countDistinct())
            .from(webtoonEpisodeJpaEntity)
            .leftJoin(webtoonEpisodeJpaEntity.webtoonImages, webtoonImageJpaEntity)
            .leftJoin(webtoonEpisodeJpaEntity.webtoon(), webtoonProductJpaEntity)
            .where(makeBooleanExpression(query))
            .fetchOne() ?: 0L
    }

    private fun makeBooleanExpression(query: WebtoonEpisodeQuery): BooleanBuilder {
        val builder = BooleanBuilder()
        query.id?.let { builder.and(webtoonEpisodeJpaEntity.id.eq(it)) }
        query.webtoonId?.let { builder.and(webtoonEpisodeJpaEntity.webtoon().id.eq(it)) }
        query.uploadDateTo?.let { builder.and(webtoonEpisodeJpaEntity.uploadAt.loe(it)) }
        return builder
    }
}