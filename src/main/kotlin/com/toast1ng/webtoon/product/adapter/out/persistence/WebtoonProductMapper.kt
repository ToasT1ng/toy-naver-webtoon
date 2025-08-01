package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.GenreJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonProductJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonWithPersonDto
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonWithEpisodeProjection
import com.toast1ng.webtoon.product.domain.Creator
import com.toast1ng.webtoon.product.domain.CreatorRole
import com.toast1ng.webtoon.product.domain.DayOfWeek
import com.toast1ng.webtoon.product.domain.Genre
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
            likes = jpaEntity.likes,
            mainThumbnail = jpaEntity.mainThumbnail ?: "",
            subThumbnail = jpaEntity.mainThumbnail ?: "",
            day = DayOfWeek.fromValue(jpaEntity.day),
            restrictedAge = jpaEntity.restrictAge,
            status = WebtoonStatus.valueOf(jpaEntity.status),
            genres = listOf(jpaEntity.genre.let { Genre(it.id ?: 0L, it.name, it.description ?: "") }),
            creators = jpaEntity.creators.map {
                Creator(
                    personId = it.person.id ?: 0L,
                    webtoonId = jpaEntity.id ?: 0L,
                    name = it.person.name,
                    role = CreatorRole.fromValue(it.person.role),
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
            likes = dto.likes,
            mainThumbnail = dto.mainThumbnailLink ?: "",
            subThumbnail = dto.subThumbnailLink ?: "",
            day = DayOfWeek.fromValue(dto.dayOfWeek),
            restrictedAge = dto.restrictedAge,
            status = WebtoonStatus.valueOf(dto.status),
            genres = emptyList(),
            creators = dto.creators.map {
                Creator(
                    personId = it.personId,
                    webtoonId = dto.id,
                    name = it.name,
                    role = CreatorRole.fromValue(it.role),
                )
            },
        )
    }

    fun mapToEntity(projection: WebtoonWithEpisodeProjection): WebtoonProduct {
        return WebtoonProduct(
            id = projection.webtoonId,
            title = projection.title,
            description = projection.description,
            views = projection.views,
            rating = projection.rating,
            likes = projection.likes,
            mainThumbnail = projection.mainThumbnail ?: "",
            subThumbnail = projection.subThumbnail ?: "",
            day = DayOfWeek.fromValue(projection.day),
            restrictedAge = projection.restrictAge,
            status = WebtoonStatus.valueOf(projection.status),
            genres = listOf(projection.genre.let { Genre(it.id ?: 0L, it.name, it.description ?: "") }),
            creators = emptyList(),
        )
    }

    fun mapToJpaEntity(entiy: WebtoonProduct): WebtoonProductJpaEntity {
        return WebtoonProductJpaEntity(
            id = entiy.id.takeIf { it != 0L },
            title = entiy.title,
            description = entiy.description,
            views = entiy.views,
            rating = entiy.rating,
            likes = entiy.likes,
            mainThumbnail = entiy.mainThumbnail,
            subThumbnail = entiy.subThumbnail,
            day = entiy.day.value,
            restrictAge = entiy.restrictedAge,
            status = entiy.status.name,
            isDeleted = false, //TODO: 구현필요
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            genre = entiy.genres.firstOrNull()?.let {
                GenreJpaEntity(it.id, it.name, it.description)
            } ?: GenreJpaEntity(0L, "", ""), //TODO: many to many 이후 수정 필요
        )
    }
}