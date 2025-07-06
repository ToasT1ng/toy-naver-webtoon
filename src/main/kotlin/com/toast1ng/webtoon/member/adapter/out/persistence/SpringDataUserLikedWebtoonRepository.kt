package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserLikedWebtoonJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataUserLikedWebtoonRepository : JpaRepository<UserLikedWebtoonJpaEntity, Long> {
}