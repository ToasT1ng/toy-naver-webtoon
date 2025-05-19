package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonProductJpaEntity
import com.toast1ng.webtoon.product.application.port.out.GetWebtoonProductQuery

interface QueryDslWebtoonProductRepository {
    fun find(query: GetWebtoonProductQuery): WebtoonProductJpaEntity?
    fun findAll(query: GetWebtoonProductQuery): List<WebtoonProductJpaEntity>
}