package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.annotations.PersistenceAdapter
import com.toast1ng.webtoon.product.application.port.out.query.WebtoonEpisodeCommentQuery
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeComment

@PersistenceAdapter
class WebtoonEpisodeCommentPersistenceAdapter(
    private val repository: SpringDataWebtoonEpisodeCommentRepository,
    private val mapper: WebtoonEpisodeCommentMapper
) {
    fun findTopCommentsByEpisodeId(episodeId: Long): List<WebtoonEpisodeComment> {
        return repository.findAll(WebtoonEpisodeCommentQuery(webtoonEpisodeId = episodeId)).map { mapper.mapToEntity(it) }
    }

    fun findChildCommentsByParentId(parentId: Long): List<WebtoonEpisodeComment> {
        return repository.findAll(WebtoonEpisodeCommentQuery(parentId = parentId)).map { mapper.mapToEntity(it) }
    }

    fun saveComment(comment: WebtoonEpisodeComment) {
        repository.save(mapper.mapToJpaEntity(comment))
    }
}