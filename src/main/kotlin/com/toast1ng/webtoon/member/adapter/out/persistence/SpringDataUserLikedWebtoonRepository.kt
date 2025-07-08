package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserLikedWebtoonJpaEntity
import com.toast1ng.webtoon.member.adapter.out.persistence.querydsl.QueryDslUserLikedWebtoonRepository
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataUserLikedWebtoonRepository : JpaRepository<UserLikedWebtoonJpaEntity, Long>, QueryDslUserLikedWebtoonRepository {
}