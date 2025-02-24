package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.PersistenceAdapter
import com.toast1ng.webtoon.product.application.port.out.MakeWebtoonPort
import com.toast1ng.webtoon.product.domain.WebtoonProduct

@PersistenceAdapter
class WebtoonProductPersistenceAdapter(
    private val mapper: WebtoonProductMapper,
    private val webtoonProductRepository: SpringDataWebtoonProductRepository
) : MakeWebtoonPort {
    override fun makeWebtoon(webtoonProduct: WebtoonProduct) {
        webtoonProductRepository.save(mapper.mapToJpaEntity(webtoonProduct))
    }
}