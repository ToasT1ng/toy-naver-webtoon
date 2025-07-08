package com.toast1ng.webtoon.member.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserLikedWebtoonJpaEntity
import com.toast1ng.webtoon.member.application.port.out.query.UserLikedWebtoonQuery

interface QueryDslUserLikedWebtoonRepository {
    fun find(query: UserLikedWebtoonQuery): UserLikedWebtoonJpaEntity?
    fun findAll(query: UserLikedWebtoonQuery): List<UserLikedWebtoonJpaEntity>
}