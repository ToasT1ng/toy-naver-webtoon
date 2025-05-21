package com.toast1ng.webtoon.product.adapter.out.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "webtoon_episode")
class WebtoonEpisodeJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "webtoon_id", nullable = false, length = 50)
    val webtoonId: Long,

    @Column(name = "seq", nullable = false)
    val episodeNumber: Int,

    @Column(nullable = false, length = 255)
    val title: String,

    @Column(precision = 3, scale = 2)
    val rating: BigDecimal,

    @Column(name = "thumbnail_link", nullable = false, length = 500)
    val thumbnailLink: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)