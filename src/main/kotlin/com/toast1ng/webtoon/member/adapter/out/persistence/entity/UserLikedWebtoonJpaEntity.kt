package com.toast1ng.webtoon.member.adapter.out.persistence.entity

import com.toast1ng.webtoon.product.adapter.out.persistence.entity.WebtoonProductJpaEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_liked_webtoon")
data class UserLikedWebtoonJpaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id", nullable = false)
    val webtoon: WebtoonProductJpaEntity,

    @Column(name = "status", nullable = false)
    val status: String,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
)