package com.toast1ng.webtoon.product.adapter.out.persistence

import com.toast1ng.webtoon.member.adapter.out.persistence.UserMapper
import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonEpisodeCommentJpaEntity
import com.toast1ng.webtoon.product.domain.WebtoonEpisodeComment
import org.springframework.stereotype.Component

@Component
class WebtoonEpisodeCommentMapper(
    private val userMapper: UserMapper,
) {
    fun mapToEntity(jpaEntity: WebtoonEpisodeCommentJpaEntity): WebtoonEpisodeComment {
        return WebtoonEpisodeComment(
            id = jpaEntity.id ?: 0L,
            parentId = jpaEntity.parentId,
            episodeId = jpaEntity.webtoonEpisodeId,
            user = userMapper.mapToEntity(jpaEntity.webtoonUser),
            content = jpaEntity.content,
            createdAt = jpaEntity.createdAt,
            updatedAt = jpaEntity.updatedAt
        )
    }

    fun mapToJpaEntity(comment: WebtoonEpisodeComment): WebtoonEpisodeCommentJpaEntity {
        return WebtoonEpisodeCommentJpaEntity(
            id = comment.id.takeIf { it != 0L },
            parentId = comment.parentId,
            webtoonEpisodeId = comment.episodeId,
            webtoonUser = userMapper.mapToJpaEntity(comment.user),
            content = comment.content,
            createdAt = comment.createdAt,
            updatedAt = comment.updatedAt
        )
    }
}