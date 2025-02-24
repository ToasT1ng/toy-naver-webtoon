package com.toast1ng.webtoon.product.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataWebtoonProductRepository : JpaRepository<WebtoonProductJpaEntity, String>