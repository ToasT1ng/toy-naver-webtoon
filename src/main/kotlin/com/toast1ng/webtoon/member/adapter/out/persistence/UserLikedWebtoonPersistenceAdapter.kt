package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.annotations.PersistenceAdapter
import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.ToggleWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.query.UserLikedWebtoonQuery
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon

@PersistenceAdapter
class UserLikedWebtoonPersistenceAdapter(
    private val repository: SpringDataUserLikedWebtoonRepository,
    private val mapper: UserLikedWebtoonMapper
) : ToggleWebtoonLikePort, ReadWebtoonLikePort {
    override fun toggleWebtoonLike(userLikedWebtoon: UserLikedWebtoon): UserLikedWebtoon {
        return repository.save(mapper.mapToJpaEntity(userLikedWebtoon)).let {
            mapper.mapToEntity(it)
        }
    }

    override fun getWebtoonLikes(query: UserLikedWebtoonQuery): List<UserLikedWebtoon> {
        return repository.findAll(query).map { mapper.mapToEntity(it) }
    }
}