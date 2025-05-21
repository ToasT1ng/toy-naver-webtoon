package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import org.springframework.stereotype.Component

@Component
class WebtoonEpisodeMapper {
    fun mapToEntity(jpaEntity: WebtoonEpisodeJpaEntity): WebtoonEpisode {
        return WebtoonEpisode(
            id = jpaEntity.id ?: 0L,
            title = jpaEntity.title,
            episodeNumber = jpaEntity.episodeNumber,
            episodeTitle = jpaEntity.title,
            rating = jpaEntity.rating,
            thumbnail = jpaEntity.thumbnailLink,
            uploadDate = jpaEntity.createdAt,
            images = emptyList()
        )
    }
}