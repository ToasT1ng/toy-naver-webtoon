package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonProductJpaEntity
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonProductQuery

interface QueryDslWebtoonProductRepository {
    fun find(query: WebtoonProductQuery): WebtoonProductJpaEntity?
    fun findAll(query: WebtoonProductQuery): List<WebtoonProductJpaEntity>
    fun count(query: WebtoonProductQuery): Long
}