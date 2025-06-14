package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.PersistenceAdapter
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonEpisodeNavigationPort
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonEpisodePort
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeNavigationQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodePagingQuery
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeQuery
import com.toast1ng.webtoon.product.domain.WebtoonEpisode
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeNavigation
import org.springframework.data.domain.Page

@PersistenceAdapter
class WebtoonEpisodePersistenceAdapter(
    private val mapper: WebtoonEpisodeMapper,
    private val repository: SpringDataWebtoonEpisodeRepository
) : ReadWebtoonEpisodePort, ReadWebtoonEpisodeNavigationPort{
    override fun getWebtoonEpisode(query: WebtoonEpisodeQuery): WebtoonEpisode? {
        return repository.find(query)?.let { mapper.mapToEntity(it) }
    }

    override fun getPagingWebtoonEpisodes(
        query: WebtoonEpisodePagingQuery,
    ): Page<WebtoonEpisode> {
        return repository.findAll(query).map (
            mapper::mapToEntity
        )
    }

    override fun getWebtoonEpisodeNavigation(query: WebtoonEpisodeNavigationQuery): WebtoonEpisodeNavigation {
        return repository.findNavigationIdsByEpisodeNumber(query).let {
            mapper.mapToEntity(it, query.currentEpisodeId)
        }
    }
}