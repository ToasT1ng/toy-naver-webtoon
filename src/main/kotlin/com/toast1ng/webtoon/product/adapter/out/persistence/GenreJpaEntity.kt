package com.toast1ng.webtoon.product.adapter.out.persistence

import jakarta.persistence.*

@Table(name = "genre")
@Entity
class GenreJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val name: String,

    val description: String? = null
)