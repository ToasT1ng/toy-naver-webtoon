package com.toast1ng.webtoon.product.adapter.out.persistence.entity

import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserJpaEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "webtoon_episode_comment")
data class WebtoonEpisodeCommentJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "parent_id")
    val parentId: Long = 0,

    @Column(name = "webtoon_episode_id")
    val webtoonEpisodeId: Long,

    @JoinColumn(name = "webtoon_users_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    val webtoonUser: UserJpaEntity,

    @Column(columnDefinition = "TEXT")
    val content: String,

    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime,
)