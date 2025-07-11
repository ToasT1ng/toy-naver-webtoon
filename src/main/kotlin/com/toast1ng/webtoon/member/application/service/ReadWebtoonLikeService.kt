package com.toast1ng.webtoon.member.application.service

import com.toast1ng.webtoon.member.application.port.`in`.GetWebtoonLikeUseCase
import com.toast1ng.webtoon.member.application.port.out.ReadWebtoonLikePort
import com.toast1ng.webtoon.member.application.port.out.query.UserLikedWebtoonQuery
import com.toast1ng.webtoon.member.domain.UserLikedWebtoon
import com.toast1ng.webtoon.member.domain.WebtoonLikedStatus
import com.toast1ng.webtoon.member.domain.toQueryString
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ReadWebtoonLikeService(
    private val readWebtoonLikePort: ReadWebtoonLikePort,
) : GetWebtoonLikeUseCase {
    override fun getUserLikedWebtoons(userId: Long): List<UserLikedWebtoon> {
        return readWebtoonLikePort.getWebtoonLikes(
            UserLikedWebtoonQuery(
                userId = userId,
                status = WebtoonLikedStatus.LIKED.toQueryString()
            )
        )
    }

    override fun getUserLikedWebtoon(
        userId: Long,
        webtoonId: Long
    ): UserLikedWebtoon {
        return readWebtoonLikePort.getWebtoonLike(
            UserLikedWebtoonQuery(
                userId = userId,
            )
        ) ?: throw IllegalArgumentException(
            "User liked webtoon with userId $userId and webtoonId $webtoonId does not exist."
        )
    }
}