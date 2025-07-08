package com.toast1ng.webtoon.member.application.port.out

import com.toast1ng.webtoon.member.application.port.out.query.UserLikedWebtoonQuery
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon

interface ReadWebtoonLikePort {
    fun getWebtoonLikes(query: UserLikedWebtoonQuery): List<UserLikedWebtoon>
}