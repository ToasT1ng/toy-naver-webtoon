package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.annotations.PersistenceAdapter
import com.toast1ng.webtoon.product.application.port.out.MakeWebtoonEpisodeCommentPort
import com.toast1ng.webtoon.product.application.port.out.ReadWebtoonEpisodeCommentPort
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeCommentQuery
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeComment

@PersistenceAdapter
class WebtoonEpisodeCommentPersistenceAdapter(
    private val repository: SpringDataWebtoonEpisodeCommentRepository,
    private val mapper: WebtoonEpisodeCommentMapper
) : ReadWebtoonEpisodeCommentPort, MakeWebtoonEpisodeCommentPort {
    override fun findTopCommentsByEpisodeId(episodeId: Long): List<WebtoonEpisodeComment> {
        return repository.findAll(WebtoonEpisodeCommentQuery(parentId = 0L, webtoonEpisodeId = episodeId))
            .map { mapper.mapToEntity(it) }
    }

    override fun findChildCommentsByParentId(parentId: Long): List<WebtoonEpisodeComment> {
        return repository.findAll(WebtoonEpisodeCommentQuery(parentId = parentId)).map { mapper.mapToEntity(it) }
    }

    override fun saveComment(comment: WebtoonEpisodeComment) {
        repository.save(mapper.mapToJpaEntity(comment))
    }
}