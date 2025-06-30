package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.CreatorJpaEntity
import com.toast1ng.webtoon.product.domain.Creator
import com.toast1ng.webtoon.product.domain.CreatorRole
import org.springframework.stereotype.Component

@Component
class CreatorMapper {
    fun mapToEntity(jpaEntity: CreatorJpaEntity): Creator {
        return Creator(
            personId = jpaEntity.person.id ?: 0L,
            webtoonId = jpaEntity.webtoon.id ?: 0L,
            name = jpaEntity.person.name,
            role = CreatorRole.fromValue(jpaEntity.person.role),
        )
    }
}