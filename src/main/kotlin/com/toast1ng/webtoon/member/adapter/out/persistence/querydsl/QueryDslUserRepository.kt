package com.toast1ng.webtoon.member.adapter.out.persistence.querydsl

import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserJpaEntity
import com.toast1ng.webtoon.member.application.port.out.query.UserQuery

interface QueryDslUserRepository {
    fun find(query: UserQuery): UserJpaEntity?
    fun findFirst(query: UserQuery): UserJpaEntity?
    fun exists(query: UserQuery): Boolean
}