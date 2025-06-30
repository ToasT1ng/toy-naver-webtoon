package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.annotations.PersistenceAdapter
import com.toast1ng.webtoon.product.application.port.out.ReadCreatorPort
import com.toast1ng.webtoon.product.domain.Creator

@PersistenceAdapter
class CreatorPersistenceAdapter(
    private val mapper: CreatorMapper,
    private val repository: SpringDataCreatorRepository,
) : ReadCreatorPort {
    override fun getCreatorsByWebtoonIds(webtoonIds: List<Long>): List<Creator> {
        return repository.findAllByWebtoonIds(webtoonIds).map {
            mapper.mapToEntity(it)
        }
    }
}