package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.PersistenceAdapter
import com.toast1ng.webtoon.product.application.port.out.GetWebtoonsQuery
import com.toast1ng.webtoon.product.application.port.out.GetWebtoonQuery
import com.toast1ng.webtoon.product.application.port.out.MakeWebtoonPort
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonProductPort
import com.toast1ng.webtoon.product.domain.WebtoonProduct

//TODO
// 1. n+1 문제 해결
// 2. queryDSL로 변경
@PersistenceAdapter
class WebtoonProductPersistenceAdapter(
    private val mapper: WebtoonProductMapper,
    private val webtoonProductRepository: SpringDataWebtoonProductRepository
) : MakeWebtoonPort, ReadWebtoonProductPort {
    override fun makeWebtoon(webtoonProduct: WebtoonProduct) {
        TODO()
    }

    override fun getWebtoons(query: GetWebtoonsQuery): List<WebtoonProduct> {
        return webtoonProductRepository.findAllByDay(query.day?.value!!).map {
            mapper.mapToEntity(it)
        }
    }

    override fun getWebtoon(query: GetWebtoonQuery): WebtoonProduct? {
        return webtoonProductRepository.findById(query.id!!).let {
            if (it.isPresent) {
                mapper.mapToEntity(it.get())
            } else {
                null
            }
        }
    }
}