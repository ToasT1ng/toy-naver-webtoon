package com.toast1ng.webtoon.member.adapter.out.persistence

import com.toast1ng.webtoon.common.domain.annotations.PersistenceAdapter

@PersistenceAdapter
class UserLikedWebtoonPersistenceAdapter(
    private val repository: SpringDataUserLikedWebtoonRepository
) {
}