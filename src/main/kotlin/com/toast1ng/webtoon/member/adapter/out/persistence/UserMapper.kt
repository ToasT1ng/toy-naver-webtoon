package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserJpaEntity
import com.toast1ng.webtoon.member.domain.User
import com.toast1ng.webtoon.member.domain.UserRole
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun mapToEntity(jpaEntity: UserJpaEntity): User {
        return User(
            id = jpaEntity.id ?: 0L,
            username = jpaEntity.username,
            password = jpaEntity.password,
            role = UserRole.valueOf(jpaEntity.role)
        )
    }

    fun mapToJpaEntity(user: User): UserJpaEntity {
        return UserJpaEntity(
            id = user.id.takeIf { it != 0L },
            username = user.username,
            password = user.password,
            role = user.role.name
        )
    }
}