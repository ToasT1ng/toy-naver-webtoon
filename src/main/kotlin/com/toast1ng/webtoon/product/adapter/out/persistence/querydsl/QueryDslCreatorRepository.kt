package com.toast1ng.webtoon.product.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.CreatorJpaEntity

interface QueryDslCreatorRepository {
    fun findAllByWebtoonIds(webtoonIds: List<Long>): List<CreatorJpaEntity>
}