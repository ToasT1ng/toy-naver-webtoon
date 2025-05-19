package com.toast1ng.webtoon.product.adapter.out.persistence.entity

import jakarta.persistence.*

@Table(name = "creator")
@Entity
class CreatorJpaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    val webtoon: WebtoonProductJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    val person: PersonJpaEntity
)