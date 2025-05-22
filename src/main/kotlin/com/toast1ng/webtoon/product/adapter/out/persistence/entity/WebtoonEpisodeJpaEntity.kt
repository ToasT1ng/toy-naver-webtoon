package com.toast1ng.webtoon.product.adapter.out.persistence.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "webtoon_episode")
class WebtoonEpisodeJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

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
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "upload_at", nullable = false, updatable = false)
    var uploadAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    val webtoon: WebtoonProductJpaEntity,

    @OneToMany(mappedBy = "webtoonEpisode", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val webtoonImages: List<WebtoonImageJpaEntity> = emptyList(),
)