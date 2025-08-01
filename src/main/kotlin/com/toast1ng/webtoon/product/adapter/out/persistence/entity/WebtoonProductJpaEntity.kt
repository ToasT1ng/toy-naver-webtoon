package com.toast1ng.webtoon.product.adapter.out.persistence.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Table(name = "webtoon")
@Entity
class WebtoonProductJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val description: String,
    val views: Int,
    val rating: BigDecimal,
    val likes: Int,
    @Column(name="main_thumbnail_link")
    val mainThumbnail: String?,
    @Column(name="sub_thumbnail_link")
    val subThumbnail: String?,
    @Column(name="day_of_week")
    val day: String,
    @Column(name="restrict_age")
    val restrictAge: Int,
    val status: String,
    @Column(name="is_deleted")
    val isDeleted: Boolean,
    @Column(name="created_at")
    val createdAt: LocalDateTime,
    @Column(name="updated_at")
    val updatedAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    val genre: GenreJpaEntity,  //TODO many to many

    @OneToMany(mappedBy = "webtoon", fetch = FetchType.LAZY)
    val creators: List<CreatorJpaEntity> = emptyList(),
)