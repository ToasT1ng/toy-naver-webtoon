package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.member.adapter.out.persistence.entity.UserLikedWebtoonJpaEntity
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon
import com.toast1ng.webtoon.member.domain.WebtoonLikedStatus
import com.toast1ng.webtoon.product.adapter.out.persistence.WebtoonProductMapper
import org.springframework.stereotype.Component

@Component
class UserLikedWebtoonMapper(
    private val userMapper: UserMapper,
    private val webtoonMapper: WebtoonProductMapper
    //TODO: 의존성 분리 및 더 나은 방식으로 개선 필요
) {
    fun mapToEntity(jpaEntity: UserLikedWebtoonJpaEntity): UserLikedWebtoon {
        return UserLikedWebtoon(
            id = jpaEntity.id ?: 0L,
            user = userMapper.mapToEntity(jpaEntity.user),
            webtoon = webtoonMapper.mapToEntity(jpaEntity.webtoon),
            status = WebtoonLikedStatus.valueOf(jpaEntity.status),
            updatedAt = jpaEntity.updatedAt
        )
    }

    fun mapToJpaEntity(entity: UserLikedWebtoon): UserLikedWebtoonJpaEntity {
        return UserLikedWebtoonJpaEntity(
            id = entity.id.takeIf { it != 0L },
            user = userMapper.mapToJpaEntity(entity.user),
            webtoon = webtoonMapper.mapToJpaEntity(entity.webtoon),
            status = entity.status.name,
            updatedAt = entity.updatedAt
        )
    }
}