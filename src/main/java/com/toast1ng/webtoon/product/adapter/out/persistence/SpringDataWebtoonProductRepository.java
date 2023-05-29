package com.toast1ng.webtoon.product.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataWebtoonProductRepository extends JpaRepository<WebtoonProductJpaEntity, String> {
}
