package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeJpaEntity
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeNavigationDto
import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeNavigation
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
            uploadDate = jpaEntity.uploadAt,
            images = jpaEntity.webtoonImages.map { it.imgLink },
            webtoon = jpaEntity.webtoon.let {
                WebtoonEpisode.WebtoonProductBriefInfo(
                    id = it.id ?: 0L,
                    title = it.title,
                )
            }
        )
    }

    fun mapToEntity(dto: WebtoonEpisodeNavigationDto, currentEpisodeId: Long): WebtoonEpisodeNavigation {
        return WebtoonEpisodeNavigation(
            previousEpisodeId = dto.previousEpisodeId,
            currentEpisodeId = currentEpisodeId,
            nextEpisodeId = dto.nextEpisodeId
        )
    }
}