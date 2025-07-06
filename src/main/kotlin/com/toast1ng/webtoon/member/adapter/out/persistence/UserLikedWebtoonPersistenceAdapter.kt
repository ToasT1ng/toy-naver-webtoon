package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.annotations.PersistenceAdapter
import com.toast1ng.webtoon.member.application.port.out.ToggleWebtoonLikePort
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon

@PersistenceAdapter
class UserLikedWebtoonPersistenceAdapter(
    private val repository: SpringDataUserLikedWebtoonRepository,
    private val mapper: UserLikedWebtoonMapper
) : ToggleWebtoonLikePort {
    override fun toggleWebtoonLike(userLikedWebtoon: UserLikedWebtoon): UserLikedWebtoon {
        return repository.save(mapper.mapToJpaEntity(userLikedWebtoon)).let {
            mapper.mapToEntity(it)
        }
    }
}