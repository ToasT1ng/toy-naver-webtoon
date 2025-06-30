package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.CreatorJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.querydsl.QueryDslCreatorRepository
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataCreatorRepository : JpaRepository<CreatorJpaEntity, Long>, QueryDslCreatorRepository