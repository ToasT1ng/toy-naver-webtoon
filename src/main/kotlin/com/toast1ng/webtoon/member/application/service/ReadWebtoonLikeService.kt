package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.GetWebtoonLikeUseCase
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ReadWebtoonLikeService : GetWebtoonLikeUseCase {
    override fun getUserLikedWebtoons(userId: Long): List<UserLikedWebtoon> {
        TODO("Not yet implemented")
    }
}