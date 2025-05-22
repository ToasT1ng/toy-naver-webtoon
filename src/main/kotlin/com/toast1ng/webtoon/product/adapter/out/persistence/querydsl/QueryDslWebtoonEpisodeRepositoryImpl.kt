package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonEpisodeJpaEntity.webtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonImageJpaEntity.webtoonImageJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.QWebtoonProductJpaEntity.webtoonProductJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.application.port.out.GetWebtoonEpisodeQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class QueryDslWebtoonEpisodeRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QueryDslWebtoonEpisodeRepository {
    override fun find(query: GetWebtoonEpisodeQuery): WebtoonEpisodeJpaEntity? {
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

    //TODO: 페이징 처리 최적화
    override fun findAll(query: GetWebtoonEpisodeQuery, pageable: Pageable): Page<WebtoonEpisodeJpaEntity> {
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

        val total = queryFactory
            .select(webtoonEpisodeJpaEntity.count())
            .from(webtoonEpisodeJpaEntity)
            .where(makeBooleanExpression(query))
            .fetchOne() ?: 0L

        return PageImpl(content, pageable, total)
    }

    private fun makeBooleanExpression(query: GetWebtoonEpisodeQuery): BooleanBuilder {
        val builder = BooleanBuilder()
        query.id?.let { builder.and(webtoonEpisodeJpaEntity.id.eq(it)) }
        query.webtoonId?.let { builder.and(webtoonEpisodeJpaEntity.webtoon().id.eq(it)) }
        query.uploadDateTo?.let { builder.and(webtoonEpisodeJpaEntity.uploadAt.loe(it)) }
        return builder
    }
}