package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.domain.Creator
import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import com.toast1ng.webtoon.product.domain.WebtoonStatus
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class WebtoonProductMapper {
    fun mapToEntity(jpaEntity: WebtoonProductJpaEntity): WebtoonProduct {
        return WebtoonProduct(
            id = jpaEntity.id ?: 0L,
            title = jpaEntity.title,
            description = jpaEntity.description,
            views = jpaEntity.views,
            rating = jpaEntity.rating,
            mainThumbnail = jpaEntity.mainThumbnail ?: "",
            subThumbnail = jpaEntity.mainThumbnail ?: "",
            day = DayOfWeek.fromValue(jpaEntity.day),
            status = WebtoonStatus.valueOf(jpaEntity.status),
            creators = jpaEntity.creators.map {
                Creator(
                    personId = it.person.id ?: 0L,
                    name = it.person.name,
                    role = it.person.role,
                )
            },
        )
    }
}