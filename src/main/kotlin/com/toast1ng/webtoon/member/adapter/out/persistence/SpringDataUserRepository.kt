package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserJpaEntity
import com.toast1ng.webtoon.member.adapter.out.persistence.querydsl.QueryDslUserRepository
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataUserRepository : JpaRepository<UserJpaEntity, Long>, QueryDslUserRepository {
}