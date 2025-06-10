package com.toast1ng.webtoon.member.adapter.out.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "webtoon_users")
data class UserJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true)
    val username: String,
    val password: String,
    val email: String? = null,
    val nickname: String? = null,
    @Column(name = "profile_img")
    val profileImage: String? = null,
    val role: String,
    @Column(name="created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name="updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)