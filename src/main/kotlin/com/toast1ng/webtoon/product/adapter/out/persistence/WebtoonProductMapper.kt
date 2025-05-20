package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonProductJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonWithPersonDto
import com.toast1ng.webtoon.product.domain.Creator
import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.product.domain.Genre
import com.toast1ng.webtoon.product.domain.WebtoonProduct
import com.toast1ng.webtoon.product.domain.WebtoonStatus
import org.springframework.stereotype.Component

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
            genres = listOf(jpaEntity.genre.let { Genre(it.id ?: 0L, it.name, it.description ?: "") }),
            creators = jpaEntity.creators.map {
                Creator(
                    personId = it.person.id ?: 0L,
                    name = it.person.name,
                    role = it.person.role,
                )
            },
        )
    }

    fun mapToEntity(dto: WebtoonWithPersonDto): WebtoonProduct {
        return WebtoonProduct(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            views = dto.views,
            rating = dto.rating,
            mainThumbnail = dto.mainThumbnailLink ?: "",
            subThumbnail = dto.subThumbnailLink ?: "",
            day = DayOfWeek.fromValue(dto.dayOfWeek),
            status = WebtoonStatus.valueOf(dto.status),
            genres = emptyList(),
            creators = dto.creators.map {
                Creator(
                    personId = it.personId,
                    name = it.name,
                    role = it.role,
                )
            },
        )
    }
}