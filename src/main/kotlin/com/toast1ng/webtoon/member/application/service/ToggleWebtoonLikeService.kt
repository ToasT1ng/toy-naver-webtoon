package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.ToggleWebtoonLikeUseCase
import com.toast1ng.webtoon.member.application.port.out.ToggleWebtoonLikePort
import com.toast1ng.webtoon.member.domain.User
import org.springframework.stereotype.Service

@Service
class ToggleWebtoonLikeService(
    private val toggleWebtoonLikePort: ToggleWebtoonLikePort,
): ToggleWebtoonLikeUseCase {
    override fun likeWebtoon(user: User, webtoonId: Long) {
        TODO("Not yet implemented")
    }

    override fun unlikeWebtoon(user: User, webtoonId: Long) {
        TODO("Not yet implemented")
    }
}